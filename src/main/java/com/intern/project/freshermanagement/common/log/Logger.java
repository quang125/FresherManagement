//package com.intern.project.freshermanagement.common.log;
//
//import lombok.AccessLevel;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.map.PassiveExpiringMap;
//
///**
// * Main log class
// *
// * @author QuangNN
// */
//@Slf4j
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public class Logger {
//    private static final PassiveExpiringMap<String, Throwable> MAP = new PassiveExpiringMap<>(new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<>(
//            30, TimeUnit.MINUTES));
//
//    public static void info(String info) {
//        log.info(info);
//    }
//
//    public static void warn(String warn) {
//        log.warn(warn);
//    }
//
//    public static void debug(String debug) {
//        log.debug(debug);
//    }
//
//    public static void error(Throwable error) {
//        error(error.getMessage(), error);
//    }
//
//    /**
//     * On an Exception:
//     * <ol>
//     *     <li>Save the exception to mongodb with  ErrorLog for further investigation</li>
//     *     <li>Transmit the error to Telegram for immediate action</li>
//     *     <li>Normal log</li>
//     * </ol>
//     *
//     * @param message the exception message
//     * @param error   the exception
//     */
//    public static void error(String message, Throwable error) {
//        log.error(message, error);
//        if (!MAP.containsKey(message)) {
//            MAP.put(message, error);
//            new ErrorLog(message, error).send();
//        }
//    }
//}