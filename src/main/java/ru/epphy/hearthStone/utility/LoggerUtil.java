package ru.epphy.hearthStone.utility;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

@UtilityClass
public class LoggerUtil {

    private static final String LOGGER_NAME = "HearthStone";
    private static final String CLASS_NAME = LoggerUtil.class.getSimpleName();
    private static final String LOG_TEMPLATE = "%s: %s";

    public void setLevel(@NonNull Level level) {
        Logger logger = Logger.getLogger(LOGGER_NAME);
        Handler[] handlers = logger.getHandlers();
        if (handlers.length == 0) {
            warn(CLASS_NAME, "Handler list is empty; cannot change the logger's level");
            return;
        }
        logger.setLevel(level);
        handlers[0].setLevel(level);
    }

    public void debug(@NonNull Object sender, @NonNull String debug) {
        log(Level.SEVERE, LOG_TEMPLATE.formatted(getSenderName(sender), debug));
    }

    public void info(@NonNull Object sender, @NonNull String information) {
        log(Level.SEVERE, LOG_TEMPLATE.formatted(getSenderName(sender), information));
    }

    public void warn(@NonNull Object sender, @NonNull String warning) {
        log(Level.SEVERE, LOG_TEMPLATE.formatted(getSenderName(sender), warning));
    }

    public void error(@NonNull Object sender, @NonNull String error) {
        log(Level.SEVERE, LOG_TEMPLATE.formatted(getSenderName(sender), error));
    }

    public void announce(@NonNull Object sender, @NonNull String announcement) {
        log(Level.INFO, LOG_TEMPLATE.formatted(getSenderName(sender), announcement));
    }

    private void log(@NonNull Level level, @NonNull String message) {
        Logger.getLogger(LOGGER_NAME).log(level, message);
    }

    private String getSenderName(Object o) {
        if (o instanceof String) return o.toString();
        else return o.getClass().getSimpleName();
    }
}
