package com.hbfintech.hound.plugin.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.OutputStreamAppender;
import com.hbfintech.hound.core.constant.TraceContextConstants;
import com.hbfintech.hound.core.support.HoundListener;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@HoundListener
public class LogbackHoundInitializer
{
    private volatile AtomicBoolean isAlreadyConfiged = new AtomicBoolean(false);

    private void init()
    {
        if (isAlreadyConfiged.get())
        {
            return;
        }

        Set<Appender<ILoggingEvent>> appenderSet = new HashSet<>();

        LoggerContext loggerContext = (LoggerContext) LoggerFactory
                .getILoggerFactory();

        loggerContext.getLoggerList()
                .stream()
                .filter(logger -> logger.iteratorForAppenders().hasNext())
                .collect(Collectors.toList())
                .forEach(logger -> {
                            Iterator<Appender<ILoggingEvent>> it = logger
                                    .iteratorForAppenders();
                            while (it.hasNext())
                            {
                                appenderSet.add(it.next());
                            }
                        }
                );

        for (Appender<ILoggingEvent> appenderItem : appenderSet)
        {
            OutputStreamAppender<ILoggingEvent> appender = (OutputStreamAppender<ILoggingEvent>) appenderItem;

            PatternLayoutEncoder encoder = (PatternLayoutEncoder) appender
                    .getEncoder();

            String pattern = encoder.getPattern();
            pattern = pattern + "|[%X{" +
                    TraceContextConstants.TRACE_CONTEXT_HEAD + "}]";

//            encoder.setImmediateFlush(true);
            encoder.setPattern(pattern);
            encoder.start();
        }

        isAlreadyConfiged.set(true);
    }
}
