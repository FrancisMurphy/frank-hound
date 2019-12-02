package com.hbfintech.hound.plugin.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.OutputStreamAppender;
import ch.qos.logback.core.encoder.Encoder;
import com.hbfintech.hound.core.constant.TraceContextConstants;
import com.hbfintech.hound.core.event.BaseHoundEvent;
import com.hbfintech.hound.core.listener.HoundEventListener;
import com.hbfintech.hound.core.event.HoundInitializedEvent;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author frank
 */
public class LogbackHoundInitializer implements HoundEventListener
{
    private volatile AtomicBoolean isAlreadyConfiged = new AtomicBoolean(false);

    @Override
    public void onEvent(BaseHoundEvent event)
    {
        if(event instanceof HoundInitializedEvent)
        {
            reinitLogbackPattern();
        }
    }

    /**
     * Dynamically attach the around custom tag by updating the logback appender pattern
     */
    private void reinitLogbackPattern()
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
                                Appender<ILoggingEvent> appender = it.next();
                                if(appender instanceof OutputStreamAppender)
                                {
                                    appenderSet.add(appender);
                                }
                            }
                        }
                );

        for (Appender<ILoggingEvent> appenderItem : appenderSet)
        {
            OutputStreamAppender<ILoggingEvent> appender = (OutputStreamAppender<ILoggingEvent>) appenderItem;

            //ch.qos.logback.classic
            Encoder encoder =  appender.getEncoder();
            if(encoder instanceof PatternLayoutEncoder)
            {
                PatternLayoutEncoder patternLayoutEncoder = (PatternLayoutEncoder) appender
                        .getEncoder();

                //TODO：动态配置
                String pattern = patternLayoutEncoder.getPattern();

                int lastIndex =  pattern.lastIndexOf("|");

                String traceIdPattern = "[%X{" +
                        TraceContextConstants.TRACE_CONTEXT_HEAD + "}]|";

                StringBuilder sb = new StringBuilder(pattern);

                sb.insert(lastIndex+1,traceIdPattern);

                patternLayoutEncoder.setPattern(sb.toString());
                patternLayoutEncoder.start();
            }

        }

        isAlreadyConfiged.set(true);
    }
}
