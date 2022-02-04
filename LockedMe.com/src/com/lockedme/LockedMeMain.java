package com.lockedme;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LockedMeMain {
    static String directoryPath;
    File files;

    public LockedMeMain() {
        directoryPath = System.getProperty("user.dir");
        files = new File(directoryPath+"/files");
        if (!files.exists())
            files.mkdirs();
        System.out.println("Directory Path:- "+ files.getAbsolutePath());
    }

    private static final String WELCOME_MESSAGE = 
    		"\n**************************************************************"+
    	    "\n** Welcome to Lockedme.com (Virtual Key for Your Repositories)"+		
            "\n** Developed by : Dheeraj Thoke\n"+
            "**************************************************************"+
            "\n\nYou can use this application to :-\n"+
			"• Retrieve all file names from the files folder\n"+
			"• Search, add, or delete file in the files folder\n"+
			"\n**Please enter the complete filename for searching or deleting files**\n"+
    		"***********************************************************************";

    private static final String MAIN_MENU =
            "\nMAIN MENU - Enter your choice:-\n"+
            "-------------------------------\n"+		
            "1 -> List all files\n"+
            "2 -> Add, Delete or Search\n"+
            "3 -> Exit Program";

    private static final String SECONDARY_MENU =
            "\nEnter your choice:-\n"+
        	"-------------------\n"+
        	"1 -> Add a file\n"+
        	"2 -> Delete a file\n"+
        	"3 -> Search a file\n"+
        	"4 -> Go Back to main menu";

    void showPrimaryMenu() {
        System.out.println(MAIN_MENU);
        Scanner input = new Scanner(System.in);
        try{            
            int choice = input.nextInt();
            switch (choice){
                case 1: {
                    showFiles();
                    showPrimaryMenu();
                    break;
                }
                case 2: {
                    showSecondaryMenu();
                    break;
                }
                case 3: {
                    System.out.println("Program terminated successfully");
                    input.close();
                    System.exit(0);
                    break;
                }
                default:{
                	System.out.println("Please enter a valid option");
                	showPrimaryMenu();
                }  	
              }
        	}
        catch (Exception e){
        	System.out.println(e.getClass().getName());
        	System.out.println("Error occured. Please enter a valid option");
            showPrimaryMenu();
        }
    }

    void showSecondaryMenu() {
        System.out.println(SECONDARY_MENU);
        try{
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();

            switch (choice){
                case 1: {
                    System.out.println("Enter a file name to add : ");
                    String filename = in.next().trim();
                    addFile(filename);
                    break;
                }
                case 2: {
                    System.out.println("Enter a file name to delete : ");
                    String filename = in.next().trim();
                    deleteFile(filename);
                    break;
                }
                case 3: {
                    System.out.println("Enter a file name to search : ");
                    String filename = in.next().trim();
                    searchFile(filename);
                    break;
                }
                case 4: {
                    System.out.println("Going Back to Main menu");
                    showPrimaryMenu();
                    break;
                }
                default : {
                	System.out.println("Please enter a valid option");
                	showSecondaryMenu();
            	}
            }
           in.close();
        }
        catch (Exception e){
        	System.out.println(e.getClass().getName());
        	System.out.println("Error occured. Please enter a valid option");
            showSecondaryMenu();
        }
    }

    void showFiles() {
        if (files.list().length==0)
            System.out.println("The folder is empty");
        else {
            String[] list = files.list();
            System.out.println("List of all the files in "+ files +" :\n");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
        }
    }

    void addFile(String File_name) throws IOException {
        File filepath = new File(files +"/"+File_name);
        String[] list = files.list();
        for (String file: list) {
            if (File_name.equalsIgnoreCase(file)) {
                System.out.println("File " +File_name + " already exists at " + files);
                showSecondaryMenu();
            }
        }
        filepath.createNewFile();
        System.out.println("File "+File_name+" added to "+ files);
        showSecondaryMenu();
    }

    void deleteFile(String File_name) {
        File filepath = new File(files +"/"+File_name);
        String[] list = files.list();
        for (String file: list) {
            if (File_name.equals(file)) {
                filepath.delete();
                System.out.println("File " + File_name + " deleted from " + files);
                showSecondaryMenu();
            }
        }
        System.out.println("Delete Operation failed. File not found.");
        showSecondaryMenu();
        
    }

    void searchFile(String File_name) {
        String[] list = files.list();
        for (String file: list) {
            if (File_name.equals(file)) {
                System.out.println("File Found!!! : File " + File_name + " exists at " + files);
                showSecondaryMenu();
               
            }
        }
        System.out.println("File not found");
        showSecondaryMenu();
    }

    public static void main(String[] args) {
        
        System.out.println(WELCOME_MESSAGE);
        LockedMeMain obj = new LockedMeMain();
        obj.showPrimaryMenu();

    }

}
