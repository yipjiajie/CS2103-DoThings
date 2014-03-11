import java.util.ArrayList;

public class MainLogic{
	private static final String MESSAGE_EXIT = "Exiting...";
	private static final String MESSAGE_INVALID = "Invalid command! Please try again.";
	
	private static final String DEFAULT_ADD = "add";
	private static final String DEFAULT_ADD2 = "a";
	private static final String DEFAULT_UPDATE = "update";
	private static final String DEFAULT_UPDATE2 = "u";
	private static final String DEFAULT_DELETE = "delete";
	private static final String DEFAULT_DELETE2 = "d";
	private static final String DEFAULT_LIST = "list";
	private static final String DEFAULT_LIST2 = "l";
	private static final String DEFAULT_UNDO = "undo";
	private static final String DEFAULT_UNDO2 = "un";
	private static final String DEFAULT_CUSTOM = "custom";
	private static final String DEFAULT_CUSTOM2 = "cc";
	private static final String DEFAULT_DELETE_CUSTOM = "dcustom";
	private static final String DEFAULT_DELETE_CUSTOM2 = "dc";
	private static final String DEFAULT_HELP = "help";
	private static final String DEFAULT_HELP2 = "h";
	private static final String DEFAULT_SEARCH = "search";
	private static final String DEFAULT_SEARCH2 = "s";
	private static final String DEFAULT_EXIT = "exit";
	private static final String DEFAULT_EXIT2 = "e";
	
	private enum CommandType {
		ADD, DELETE, UPDATE, LIST, UNDO, SEARCH, CUSTOM, DELETE_CUSTOM, HELP, EXIT, INVALID;
	}	

	private static CommandType getCommandType(String com) {
		
		if (com.equals(DEFAULT_ADD) || com.equals(DEFAULT_ADD2) || CustomCommandHandler.isCustomCommand(com, CustomCommandHandler.HEADER_ADD)) {
			return CommandType.ADD;
		} else if (com.equals(DEFAULT_LIST) || com.equals(DEFAULT_LIST2) || CustomCommandHandler.isCustomCommand(com, CustomCommandHandler.HEADER_READ)) {
			return CommandType.LIST;
		} else if (com.equals(DEFAULT_UPDATE) || com.equals(DEFAULT_UPDATE2) || CustomCommandHandler.isCustomCommand(com, CustomCommandHandler.HEADER_UPDATE)) {
			return CommandType.UPDATE;
		} else if (com.equals(DEFAULT_DELETE) || com.equals(DEFAULT_DELETE2) || CustomCommandHandler.isCustomCommand(com, CustomCommandHandler.HEADER_DELETE)) {
			return CommandType.DELETE;
		} else if (com.equals(DEFAULT_UNDO) || com.equals(DEFAULT_UNDO2) || CustomCommandHandler.isCustomCommand(com, CustomCommandHandler.HEADER_UNDO)) {
			return CommandType.UNDO;
		} else if (com.equals(DEFAULT_CUSTOM) || com.equals(DEFAULT_CUSTOM2) || CustomCommandHandler.isCustomCommand(com, CustomCommandHandler.HEADER_CUSTOM)) {
			return CommandType.CUSTOM;
		} else if (com.equals(DEFAULT_DELETE_CUSTOM) || com.equals(DEFAULT_DELETE_CUSTOM2) || CustomCommandHandler.isCustomCommand(com, CustomCommandHandler.HEADER_DELETE_CUSTOM)) {
			return CommandType.DELETE_CUSTOM;
		} else if (com.equals(DEFAULT_HELP) || com.equals(DEFAULT_HELP2) || CustomCommandHandler.isCustomCommand(com, CustomCommandHandler.HEADER_HELP)) {
			return CommandType.HELP;
		} else if (com.equals(DEFAULT_EXIT) || com.equals(DEFAULT_EXIT2) || CustomCommandHandler.isCustomCommand(com, CustomCommandHandler.HEADER_EXIT)) {
			return CommandType.EXIT;
		} else if (com.equals(DEFAULT_SEARCH) || com.equals(DEFAULT_SEARCH2) || CustomCommandHandler.isCustomCommand(com, CustomCommandHandler.HEADER_SEARCH)) {
			return CommandType.SEARCH;
		} else {
			return CommandType.INVALID;
		}
	} 

	protected static Feedback runLogic(String userInput) {
		String command = CommandParser.getUserCommandType(userInput);
		String commandDesc = CommandParser.getUserCommandDesc(userInput);
		
		CommandType commandType = getCommandType(command);
		
		switch (commandType) {
			case ADD:
				return TaskHandler.addTask(commandDesc);
				
			case LIST:
				return TaskHandler.listTasks();
				
			case UPDATE:
				return TaskHandler.updateTask(commandDesc);	
				
			case DELETE:
				return TaskHandler.deleteTask(commandDesc);
			
			case HELP:
				//System.out.println("Help");
				//
				
			case CUSTOM:
				String type = getCustomHeader(commandDesc);
				String customToBeAdded = CommandParser.getUserCommandDesc(commandDesc);
				return CustomCommandHandler.addCustomCommand(customToBeAdded, type);
				
			case DELETE_CUSTOM:	
				return CustomCommandHandler.deleteCustomCommand(commandDesc);
				
			case UNDO:
				return HistoryHandler.undoCommand();
				
			case SEARCH:
				//System.out.println("Search");
				//
				
			case EXIT:
				return new Feedback(MESSAGE_EXIT, true);
				
			default:
				return new Feedback(MESSAGE_INVALID, false);
		}
	}
	
	private static String getCustomHeader(String userInput) {
		String command = CommandParser.getUserCommandType(userInput);
		String commandDesc = CommandParser.getUserCommandDesc(userInput);
		
		CommandType commandType = getCommandType(command);
		
		switch (commandType) {
			case ADD:
				return CustomCommandHandler.HEADER_ADD;
			case LIST:
				return CustomCommandHandler.HEADER_READ;
				
			case UPDATE:
				return CustomCommandHandler.HEADER_UPDATE;
				
			case DELETE:
				return CustomCommandHandler.HEADER_DELETE;
			
			case HELP:
				return CustomCommandHandler.HEADER_HELP;
				
			case CUSTOM:
				return CustomCommandHandler.HEADER_CUSTOM;
				
			case DELETE_CUSTOM:	
				return CustomCommandHandler.HEADER_DELETE_CUSTOM;
				
			case UNDO:
				return CustomCommandHandler.HEADER_UNDO;
				
			case SEARCH:
				return CustomCommandHandler.HEADER_SEARCH;
				
			case EXIT:
				return CustomCommandHandler.HEADER_EXIT;
				
			default:
				return null;
		}
	}
}

