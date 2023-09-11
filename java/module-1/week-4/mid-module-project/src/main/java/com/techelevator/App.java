package com.techelevator;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.*;

public class App {


    // The regex string to split the Strings in the dataset.
    private static final String FIELD_DELIMITER = "\\|";

    private static final int TITLE_FIELD = 0;
    private static final int AUTHOR_FIELD = 1;
    private static final int PUBLISHED_YEAR_FIELD = 2;
    private static final int PRICE_FIELD = 3;

    private final Scanner keyboard = new Scanner(System.in);

    private List<String> titles = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private List<Integer> publishedYears = new ArrayList<>();
    private List<BigDecimal> prices = new ArrayList<>();

    public static void main(String[] args) {

        App app = new App();
        app.loadData();
        app.run();

    }

    private void loadData() {

        String[] dataset = Dataset.load();

        /*
         Requirement: 1
         Populate the instance variables `titles`, `authors`, `publishedYears`,
         and `prices` by splitting each string in the `dataset` array and adding
         the individual fields to the appropriate list.
         See README for additional details.
         */

        for (String data : dataset) {
                String[] fields = data.split("\\|");
                if (fields.length == 4) {
                    titles.add(fields[0]);
                    authors.add(fields[1]);
                    publishedYears.add(Integer.parseInt(fields[2]));
                    prices.add(new BigDecimal(fields[3]));
                }
            }
        }



    private void run() {

        while (true) {
            // Main menu loop
            printMainMenu();
            int mainMenuSelection = promptForMenuSelection("Please choose an option: ");
            if (mainMenuSelection == 1) {
                // Display data and subsets loop
                while (true) {
                    printDataAndSubsetsMenu();
                    int dataAndSubsetsMenuSelection = promptForMenuSelection("Please choose an option: ");
                    if (dataAndSubsetsMenuSelection == 1) {
                        displayDataset(Dataset.load());
                    } else if (dataAndSubsetsMenuSelection == 2) {
                        displayTitlesList(titles);
                    } else if (dataAndSubsetsMenuSelection == 3) {
                        displayAuthorsList(authors);
                    } else if (dataAndSubsetsMenuSelection == 4) {
                        displayPublishedYearsList(publishedYears);
                    } else if (dataAndSubsetsMenuSelection == 5) {
                        displayPricesList(prices);
                    } else if (dataAndSubsetsMenuSelection == 0) {
                        break;
                    }
                }
            }

            else if (mainMenuSelection == 2) {
                while (true) {
                    printSearchBooksMenu();
                    int searchBooksMenuSelection = promptForMenuSelection("Please choose an option: ");
                    if (searchBooksMenuSelection == 1) {
                        // Search by title
                        String filterTitle = promptForString("Enter title: ");
                        List<Integer> titleIndexes = filterByTitle(filterTitle);
                        displaySearchResults(titleIndexes);

                        /*
                         Requirement: 3b
                         Replace `displayTitlesList(titles)` with calls to the
                         `filterByTitle()` and `displaySearchResults()` methods.
                         */



                    } else if (searchBooksMenuSelection == 2) {
                        // Search by author
                        String filterAuthor = promptForString("Enter author: ");
                        List<Integer> authorIndexes = filterByAuthor(filterAuthor);
                        displaySearchResults(authorIndexes);


                        /*
                         Requirement: 4b
                         Replace `displayAuthorsList(authors)` with calls to the
                         `filterByAuthor()` and `displaySearchResults()` methods.
                         */

                    } else if (searchBooksMenuSelection == 3) {
                        // Search by published year
                        int filterYear = promptForPublishedYear("Enter date (YYYY): ");
                        List<Integer> yearIndexes = filterByPublishedYear(filterYear);
                        displaySearchResults(yearIndexes);

                        /*
                         Requirement: 5b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `filterByPublishedYear()` and `displaySearchResults()` methods.
                         */
                        displayPublishedYearsList(publishedYears);
                    } else if (searchBooksMenuSelection == 4) {
                        // Search by published year range
                        int filterFromYear = promptForPublishedYear("Enter \"from\" date (YYYY): ");
                        int filterToYear = promptForPublishedYear("Enter \"to\" date (YYYY): ");
                        List<Integer> yearRangeIndexes = filterByPublishedYearRange(filterFromYear,filterToYear);
                        displaySearchResults(yearRangeIndexes);

                        /*
                         Requirement: 6b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `filterByPublishedYearRange()` and `displaySearchResults()` methods.
                         */
                        displayPublishedYearsList(publishedYears);
                    } else if (searchBooksMenuSelection == 5) {
                        List<Integer> matchingIndexes = findMostRecentBooks();
                        displaySearchResults(matchingIndexes);

                        // Find the most recent books
                        /*

                        equirement: 7b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `findMostRecentBooks()` and `displaySearchResults()` methods.
                         */
                        displayPublishedYearsList(publishedYears);
                    } else if (searchBooksMenuSelection == 6) {
                        // Search by price
                        double filterPrice = promptForPrice("Enter price: ");
                        List<Integer> matchingIndexes = filterByPrice(filterPrice);
                        displaySearchResults(matchingIndexes);
                        /*
                         Requirement: 8b
                         Replace `displayPricesList(prices)` with calls to the
                         `filterByPrice()` and `displaySearchResults()` methods.
                         */
                        displayPricesList(prices);
                    } else if (searchBooksMenuSelection == 7) {
                        // Search by price range
                        double filterFromPrice= promptForPrice("Enter \"from\" price: ");
                        double filterToPrice = promptForPrice("Enter \"to\" price: ");
                        List<Integer> matchingIndexes = filterByPriceRange(filterFromPrice, filterToPrice);
                        displaySearchResults(matchingIndexes);
                        /*
                         Requirement: 9b
                         Replace `displayPricesList(prices)` with calls to the
                         `filterByPriceRange()` and `displaySearchResults()` methods.
                         */
                        displayPricesList(prices);
                    } else if (searchBooksMenuSelection == 8) {
                        List<Integer> leastExpensiveBooks = findLeastExpensiveBooks();

                        displaySearchResults(leastExpensiveBooks);
                        // Find the least expensive books
                        /*

                         Requirement: 10b
                         Replace `displayPricesList(prices)` with calls to the
                         `findLeastExpensiveBooks()` and `displaySearchResults()` methods.
                         */
                        displayPricesList(prices);
                    } else if (searchBooksMenuSelection == 0) {
                        break;
                    }
                }
            } else if (mainMenuSelection == 0) {
                break;
            }
        }

    }




    /*
     Requirement: 2
     Write the displaySearchResults(List<Integer> indexes) method.
     See README for additional details.
     */

    private void displaySearchResults(List<Integer> indexes) {
        System.out.print("Search Results");
        System.out.println(".............");
        for(int index : indexes) {
            System.out.println("Title: " + titles.get(index));
            System.out.println("Author: " + authors.get(index));
            System.out.println("Published Year: " + publishedYears.get(index));
            System.out.println("Price: $" + prices.get(index));
            System.out.println("...............");

        }
        promptForReturn();
    }

    /*
     Requirement: 3a
     Complete the `filterByTitle()` method.
     See README for additional details.
     */
    private List<Integer> filterByTitle(String filterTitle) {
        List<Integer> matchingIndexes = new ArrayList<>();
        for(int i = 0; i< titles.size(); i++) {
            String title =titles.get(i).toLowerCase();
            if(title.contains(filterTitle.toLowerCase())) {
                matchingIndexes.add(i);
            }
        }
        return matchingIndexes;
    }

    /*
     Requirement: 4a
     Complete the `filterByAuthor()` method.
     See README for additional details.
     */
    private List<Integer> filterByAuthor(String filterAuthor) {
        List<Integer> matchingIndexes = new ArrayList<>();
        //Loop through authors check for partial, case-insensitive matches

        for(int i = 0; i < authors.size(); i++) {
            String author = authors.get(i);
            if(author.toLowerCase().contains(filterAuthor.toLowerCase())) {
                matchingIndexes.add(i);
            }
        }

        return matchingIndexes;
    }

    /*
     Requirement: 5a
     Complete the `filterByPublishedYear()` method.
     See README for additional details.
     */
    private List<Integer> filterByPublishedYear(int filterYear) {
        List<Integer> matchingIndexes= new ArrayList<>();
        for (int i = 0; i < publishedYears.size(); i++) {
            int year = publishedYears.get(i);
            if (year== filterYear) {
                matchingIndexes.add(i);
            }
        }
        return matchingIndexes;
    }

    /*
     Requirement: 6a
     Complete the `filterByPublishedYearRange()` method.
     See README for additional details.
     */
    private List<Integer> filterByPublishedYearRange(  int filterFromYear, int filterToYear) {
        List<Integer> matchingIndexes = new ArrayList<>();
        for (int i = 0; i < publishedYears.size(); i++) {
            int year =publishedYears.get(i);
        if (year >= filterFromYear && year <= filterToYear) {
            matchingIndexes.add(i);

        }
    }
        return matchingIndexes;
    }

    /*
     Requirement: 7a
     Add the `private List<Integer> findMostRecentBooks()` method.
     See README for additional details.

     */

    private List <Integer> findMostRecentBooks() {
        List<Integer> matchingIndexes = new ArrayList<>();
        int mostRecentYear = Collections.max(publishedYears);
        for(int i = 0; i < publishedYears.size(); i++) {
            if (publishedYears.get(i) == mostRecentYear) {
                matchingIndexes.add(i);
            }
        }
        return matchingIndexes;
    }
    /*
     Requirement: 8a
     Complete the `filterByPrice()` method.
     See README for additional details.
     */
    private List<Integer> filterByPrice(double filterPrice) {
        List<Integer> matchingIndexes = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++) {
            double bookPrice = prices.get(i).doubleValue();
            if(bookPrice <= filterPrice) {
                matchingIndexes.add(i);
            }
        }
        return matchingIndexes;
    }

    /*
     Requirement: 9a
     Complete the `filterByPriceRange()` method.
     See README for additional details.
     */
    private List<Integer> filterByPriceRange(double filterFromPrice, double filterToPrice) {
        List<Integer> matchingIndexes = new ArrayList<>();
        for(int i = 0; i < prices.size(); i++) {
            double bookPrice = prices.get(i).doubleValue();
            if(bookPrice >= filterFromPrice && bookPrice <= filterToPrice) {
                matchingIndexes.add(i);

            }
        }
        return matchingIndexes;
    }


    /*
     Requirement: 10a
     Add the `private List<Integer> findLeastExpensiveBooks()` method.
     See README for additional details.
     */
    private List<Integer> findLeastExpensiveBooks() {
        List<Integer> leastExpensiveBookIndexes = new ArrayList<>();

        List<Integer> leastExpensiveIndexes = new ArrayList<>();

        // Initialize the minimum price to a large value initially
        BigDecimal minPrice = new BigDecimal(Double.MAX_VALUE);

        for (int i = 0; i < prices.size(); i++) {
            BigDecimal currentPrice = prices.get(i);


            if (currentPrice.compareTo(minPrice) < 0) {
                minPrice = currentPrice;
                leastExpensiveIndexes.clear();
                leastExpensiveIndexes.add(i);
            }

            else if (currentPrice.compareTo(minPrice) == 0) {
                leastExpensiveIndexes.add(i);
            }
        }

        return leastExpensiveIndexes;
    }






    // UI methods

    private void printMainMenu() {
        System.out.println("1: Display data and subsets");
        System.out.println("2: Search books");
        System.out.println("0: Exit");
        System.out.println();
    }

    private void printDataAndSubsetsMenu() {
        System.out.println("1: Display dataset");
        System.out.println("2: Display titles");
        System.out.println("3: Display authors");
        System.out.println("4: Display published years");
        System.out.println("5: Display prices");
        System.out.println("0: Return to main menu");
        System.out.println();
    }

    private void printSearchBooksMenu() {
        System.out.println("1: Search by title");
        System.out.println("2: Search by author");
        System.out.println("3: Search by published year");
        System.out.println("4: Search by published year range");
        System.out.println("5: Find most recent books");
        System.out.println("6: Search by price");
        System.out.println("7: Search by price range");
        System.out.println("8: Find least expensive books");
        System.out.println("0: Return to main menu");
        System.out.println();
    }

    private void displayDataset(String[] dataset) {
        System.out.println("Dataset");
        System.out.println("-------");
        for (String data : dataset) {
            System.out.println(data);
        }
        System.out.println();
        promptForReturn();
    }

    private void displayTitlesList(List<String> titles) {
        System.out.println("Titles");
        System.out.println("-------");
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(i + ": " + titles.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayAuthorsList(List<String> authors) {
        System.out.println("Authors");
        System.out.println("-------");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(i + ": " + authors.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayPublishedYearsList(List<Integer> publishedYears) {
        System.out.println("Published Years");
        System.out.println("---------------");
        for (int i = 0; i < publishedYears.size(); i++) {
            System.out.println(i + ": " + publishedYears.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayPricesList(List<BigDecimal> prices) {
        System.out.println("Prices");
        System.out.println("------");
        for (int i = 0; i < prices.size(); i++) {
            System.out.println(i + ": " + prices.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private int promptForMenuSelection(String prompt) {
        System.out.print(prompt);
        int menuSelection;
        try {
            menuSelection = Integer.parseInt(keyboard.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    private String promptForString(String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private int promptForPublishedYear(String prompt) {
        int year;
        while (true) {
            System.out.println(prompt);
            try {
                year = Integer.parseInt(keyboard.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("The year provided is not well-formed. It must be YYYY.");
            }
        }
        return year;
    }

    private double promptForPrice(String prompt) {
        double price;
        while (true) {
            System.out.println(prompt);
            try {
                price = Double.parseDouble(keyboard.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("The price provided is not a valid monetary value.");
            }
        }
        return price;
    }

    private void promptForReturn() {
        System.out.println("Press RETURN to continue.");
        keyboard.nextLine();
    }
}
