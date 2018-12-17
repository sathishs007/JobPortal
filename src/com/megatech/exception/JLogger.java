package com.megatech.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * 
 * @author Scube Technologies
 * 
 */
public class JLogger {

	private Logger logger = null;
	private static String ENTRY_MESSAGE = "Entering method {}()";
	private static String EXIT_MESSAGE = "Exiting method {}()";
	private static String MDC_METHOD_NAME_KEY = "method";

	// Constructors
	/***************************************************************************
	 * Description :Function below is a overloaded Constructor which is used to
	 * initialize this logger with the object of the underlying logging
	 * implementation.
	 * 
	 * @param loggerName
	 *            Name of the logger (as a String) for which the logging needs
	 *            to be performed.
	 **************************************************************************/

	protected JLogger(String loggerName) {
		logger = LoggerFactory.getLogger(loggerName);
	}

	/***************************************************************************
	 * Description :Function below is a overloaded Constructor which is used to
	 * initialize this logger with the object of the underlying logging
	 * implementation.
	 * 
	 * @param clazzName
	 *            Class object for the class for which the logging needs to be
	 *            performed.
	 **************************************************************************/

	protected JLogger(Class<?> clazzName) {
		logger = LoggerFactory.getLogger(clazzName);
	}

	// getLogger methods
	/***************************************************************************
	 * Description :Access method to retrieve the initialized instance of the
	 * logging component for a particular logger.
	 * 
	 * @param loggerName
	 *            Name of the logger (as a String) for which the logging needs
	 *            to be performed.
	 **************************************************************************/

	public static JLogger getLogger(String loggerName) {
		return new JLogger(loggerName);
	}

	/***************************************************************************
	 * Description :Access method to retrieve the initialized instance of the
	 * logging component for a particular logger.
	 * 
	 * @param clazzName
	 *            Class object for the class for which the logging needs to be
	 *            performed.
	 **************************************************************************/

	public static JLogger getLogger(Class<?> clazzName) {
		return new JLogger(clazzName);
	}

	// logging implementation methods.
	/***************************************************************************
	 * Description :Purpose of this Function is to check whether Debug Level
	 * logging is enabled or not
	 * 
	 * @return true if Debug level logging is enabled for this logger, false
	 *         otherwise
	 * 
	 **************************************************************************/
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to check whether info Level
	 * logging is enabled or not
	 * 
	 * @return true if info level logging is enabled for this logger, false
	 *         otherwise
	 * 
	 **************************************************************************/
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	// debug,info,error and other methods
	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message at
	 * DEBUG Level, Log will be Appended to the target as configured for the
	 * underlying logging implementation.
	 * 
	 * @param logMsg
	 *            Message to be logged. Usually passed as a String, but accepts
	 *            any object.
	 * 
	 **************************************************************************/
	public void debug(Object logMsg, Object... placeholders) {
		if (logger.isDebugEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.debug(String.valueOf(logMsg), placeholders);
			} else {
				logger.debug(String.valueOf(logMsg));
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message at
	 * DEBUG Level, Log will be Appended to the target as configured for the
	 * underlying logging implementation. Along with the message, a exception
	 * stack trace is also appended to the log for the passed exception.
	 * 
	 * @param logMsg
	 *            Message to be logged. Usually passed as a String, but accepts
	 *            any object.
	 * @param logCause
	 *            An instance of Throwable that needs to be logged along with
	 *            the corresponding stacke trace.
	 * 
	 **************************************************************************/
	public void debug(Object logMsg, Throwable logCause, Object... placeholders) {
		if (logger.isDebugEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.debug(String.valueOf(logMsg), placeholders, logCause);
			} else {
				logger.debug(String.valueOf(logMsg), logCause);
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message at INFO
	 * Level, Log will be Appended to the target as configured for the
	 * underlying logging implementation.
	 * 
	 * @param logMsg
	 *            Message to be logged. Usually passed as a String, but accepts
	 *            any object.
	 * 
	 **************************************************************************/
	public void info(Object logMsg, Object... placeholders) {
		if (logger.isInfoEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.info(String.valueOf(logMsg), placeholders);
			} else {
				logger.info(String.valueOf(logMsg));
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message at INFO
	 * Level, Log will be Appended to the target as configured for the
	 * underlying logging implementation. Along with the message, a exception
	 * stack trace is also appended to the log for the passed exception.
	 * 
	 * @param logMsg
	 *            Message to be logged. Usually passed as a String, but accepts
	 *            any object.
	 * @param logCause
	 *            An instance of Throwable that needs to be logged along with
	 *            the corresponding stacke trace.
	 * 
	 **************************************************************************/
	public void info(Object logMsg, Throwable logCause, Object... placeholders) {
		if (logger.isInfoEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.info(String.valueOf(logMsg), logCause, placeholders);
			} else {
				logger.info(String.valueOf(logMsg), logCause);
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message in WARN
	 * Level, Log will be Appended to the target configured in the configuration
	 * file(log4j.xml in our case)
	 * 
	 * Parameters :It takes only one parameter "logging Message" of type Object
	 * 
	 * Return Value :NIL
	 * 
	 * Modification Log Date Author Description
	 * ------------------------------------------------------------
	 * 
	 **************************************************************************/
	public void warn(Object logMsg, Object... placeholders) {
		if (logger.isWarnEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.warn(String.valueOf(logMsg), placeholders);
			} else {
				logger.warn(String.valueOf(logMsg));
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message at WARN
	 * Level, Log will be Appended to the target as configured for the
	 * underlying logging implementation. Along with the message, a exception
	 * stack trace is also appended to the log for the passed exception.
	 * 
	 * @param logMsg
	 *            Message to be logged. Usually passed as a String, but accepts
	 *            any object.
	 * @param logCause
	 *            An instance of Throwable that needs to be logged along with
	 *            the corresponding stacke trace.
	 * 
	 **************************************************************************/
	public void warn(Object logMsg, Throwable logCause, Object... placeholders) {
		if (logger.isWarnEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.warn(String.valueOf(logMsg), logCause, placeholders);
			} else {
				logger.warn(String.valueOf(logMsg), logCause);
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message in
	 * ERROR Level, Log will be Appended to the target configured in the
	 * configuration file(log4j.xml in our case)
	 * 
	 * Parameters :It takes only one parameter "logging Message" of type Object
	 * 
	 * Return Value :NIL
	 * 
	 * Modification Log Date Author Description
	 * ------------------------------------------------------------
	 * 
	 **************************************************************************/

	public void error(Object logMsg, Object... placeholders) {
		if (logger.isErrorEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.error(String.valueOf(logMsg), placeholders);
			} else {
				logger.error(String.valueOf(logMsg));
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message at
	 * ERROR Level, Log will be Appended to the target as configured for the
	 * underlying logging implementation. Along with the message, a exception
	 * stack trace is also appended to the log for the passed exception.
	 * 
	 * @param logMsg
	 *            Message to be logged. Usually passed as a String, but accepts
	 *            any object.
	 * @param logCause
	 *            An instance of Throwable that needs to be logged along with
	 *            the corresponding stacke trace.
	 * 
	 **************************************************************************/
	public void error(Object logMsg, Throwable logCause, Object... placeholders) {
		if (logger.isErrorEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.error(String.valueOf(logMsg), logCause, placeholders);
			} else {
				logger.error(String.valueOf(logMsg), logCause);
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message in
	 * Fatal Level, Log will be Appended to the target configured in the
	 * configuration file(log4j.xml in our case)
	 * 
	 * Parameters :It takes only one parameter "logging Message" of type Object
	 * 
	 * Return Value :NIL
	 * 
	 * Modification Log Date Author Description
	 * ------------------------------------------------------------
	 * 
	 **************************************************************************/

	public void fatal(Object logMsg, Object... placeholders) {
		if (logger.isErrorEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.error(String.valueOf(logMsg), placeholders);
			} else {
				logger.error(String.valueOf(logMsg));
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message at
	 * FATAL Level, Log will be Appended to the target as configured for the
	 * underlying logging implementation. Along with the message, a exception
	 * stack trace is also appended to the log for the passed exception.
	 * 
	 * @param logMsg
	 *            Message to be logged. Usually passed as a String, but accepts
	 *            any object.
	 * @param logCause
	 *            An instance of Throwable that needs to be logged along with
	 *            the corresponding stacke trace.
	 * 
	 **************************************************************************/
	public void fatal(Object logMsg, Throwable logCause, Object... placeholders) {
		if (logger.isErrorEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.error(String.valueOf(logMsg), logCause, placeholders);
			} else {
				logger.error(String.valueOf(logMsg), logCause);
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message in
	 * Trace Level, Log will be Appended to the target configured in the
	 * configuration file(log4j.xml in our case)
	 * 
	 * Parameters :It takes only one parameter "logging Message" of type Object
	 * 
	 * Return Value :NIL
	 * 
	 * Modification Log Date Author Description
	 * ------------------------------------------------------------
	 * 
	 **************************************************************************/

	public void trace(Object logMsg, Object... placeholders) {
		if (logger.isTraceEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.trace(String.valueOf(logMsg), placeholders);
			} else {
				logger.trace(String.valueOf(logMsg));
			}
		}
	}

	/***************************************************************************
	 * Description :Purpose of this Function is to Log the given message at
	 * TRACE Level, Log will be Appended to the target as configured for the
	 * underlying logging implementation. Along with the message, a exception
	 * stack trace is also appended to the log for the passed exception.
	 * 
	 * @param logMsg
	 *            Message to be logged. Usually passed as a String, but accepts
	 *            any object.
	 * @param logCause
	 *            An instance of Throwable that needs to be logged along with
	 *            the corresponding stacke trace.
	 * 
	 **************************************************************************/
	public void trace(Object logMsg, Throwable logCause, Object... placeholders) {
		if (logger.isTraceEnabled()) {
			addMethodNameToMDC();
			if ((placeholders != null) && (placeholders.length > 0)) {
				logger.trace(String.valueOf(logMsg), logCause, placeholders);
			} else {
				logger.trace(String.valueOf(logMsg), logCause);
			}
		}
	}

	/* Application useful methods */

	/***************************************************************************
	 * Description : Method to log the entry point in any method without passing
	 * any inputs. Method name is automatically deduced using reflection. Note:
	 * This method should not be used in interceptors.
	 **************************************************************************/
	public void entry() {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(ENTRY_MESSAGE, getCallingMethodName());
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

	/***************************************************************************
	 * Description : Method to log the exit point in any method without passing
	 * any inputs. Method name is automatically deduced using reflection. Note:
	 * This method should not be used in interceptors.
	 **************************************************************************/
	public void exit() {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(EXIT_MESSAGE, getCallingMethodName());
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

	}

	/**
	 * Adds the caller method's name on the MDC for the current thread. This is
	 * used in the logging pattern to ensure that the method name is printed out
	 * in the logs.
	 */
	private void addMethodNameToMDC() {
		MDC.put(MDC_METHOD_NAME_KEY, getCallingMethodName());
	}

	/**
	 * Deduces the method name from the logger's class that appears first on the
	 * stack trace.
	 * 
	 * @return String Name of the method calling this logging method.
	 */
	private String getCallingMethodName() {
		String methodName = "<unknown>";
		boolean foundClass = false;
		StackTraceElement[] elements = new Exception().getStackTrace();
		for (StackTraceElement element : elements) {
			if (element.getClassName().contains(logger.getName())) {
				methodName = element.getMethodName();
				foundClass = true;
				break;
			}
		}
		if (foundClass) {
			return methodName;
		} else {
			return "";
		}
	}
}