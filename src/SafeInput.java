import java.util.Scanner;

public class SafeInput
{
    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString( Scanner pipe, String prompt )
    {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print( "\n" + prompt + ": " ); // show prompt add space
            retString = pipe.nextLine();
        } while( retString.length() == 0 );

        return retString;
    }

    public static int getInt( Scanner pipe, String prompt )
    {
        boolean invalidNum = true;
        String trash = "";

        int retInt = 0;
        do
        {
            System.out.print( "\n" + prompt + ": " ); // show prompt add space
            if( pipe.hasNextInt() )
            {
                retInt = pipe.nextInt();
                pipe.nextLine();
                invalidNum = false;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println( "You must enter a valid integer: " + trash );
            }

        } while( invalidNum == true );

        return retInt;

    }

    public static double getDouble( Scanner pipe, String prompt )
    {
        boolean invalidNum = true;
        String trash = "";

        double retDouble = 0.00;
        do
        {
            System.out.print( "\n" + prompt + ": " ); // show prompt add space
            if( pipe.hasNextDouble() )
            {
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                invalidNum = false;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println( "You must enter a valid Double: " + trash );
            }

        } while( invalidNum == true );

        return retDouble;

    }

    public static int getRangedInt( Scanner pipe, String prompt, int low, int high )
    {
        int number = 0;
        boolean validInput = false;

        String rangePrompt = prompt + " [" + low + " to " + high + "]: ";

        while( !validInput )
        {
            System.out.print( rangePrompt );

            if( pipe.hasNextInt() )
            {
                number = pipe.nextInt();

                if( number >= low && number <= high )
                {
                    validInput = true;
                }
                else
                {
                    System.out.println( "Please enter a number within the range " + low + " to " + high + "." );
                }
            }
            else
            {
                System.out.println( "That's not a valid integer. Please try again." );
                pipe.next();
            }

            pipe.nextLine();
        }

        return number;
    }

    public static double getRangedDouble( Scanner pipe, String prompt, double low, double high )
    {
        double number = 0;
        boolean validInput = false;

        String rangePrompt = prompt + " [" + low + " to " + high + "]: ";

        while( !validInput )
        {
            System.out.print( rangePrompt );

            if( pipe.hasNextDouble() )
            {
                number = pipe.nextDouble();

                if( number >= low && number <= high )
                {
                    validInput = true;
                }
                else
                {
                    System.out.println( "Please enter a number within the range " + low + " to " + high + "." );
                }
            }
            else
            {
                System.out.println( "That's not a valid double. Please try again." );
                pipe.next();
            }
            pipe.nextLine();
        }
        return number;
    }

    public static boolean getYNConfirm( Scanner pipe, String prompt )
    {
        String declareEnd = "";
        boolean validInput = false;

        while( !validInput )
        {
            System.out.print( prompt + " " );

            declareEnd = pipe.nextLine();// Read input

            if( declareEnd.equalsIgnoreCase( "y" ) )
            {
                return true;  // Return true for Yes
            }
            else if( declareEnd.equalsIgnoreCase( "n" ) )
            {
                return false; // Return false for No
            }
            else
            {
                System.out.println( "Invalid input. Please enter 'Y' for Yes or 'N' for No." );
            }
        }
        return validInput;
    }

    public static String getRegExString( Scanner pipe, String prompt, String regEx )
    {

        String value = "";
        boolean gotAValue = false;
        do
        {
            System.out.println( prompt );
            value = pipe.nextLine();
            if( value.matches( regEx ) )
            {
                gotAValue = true;
            }
            else
            {
                System.out.println( "You must match the pattern " + regEx );
                System.out.println( "Try again" );
            }
        }
        while( !gotAValue );
        return value;
    }

    public static void prettyHeader( String msg )
    {
        final int totalWidth = 60;
        final int borderWidth = totalWidth - 6; // 3 stars on each side
        String border = "*".repeat( totalWidth ); // Full line of stars

        // Print the top border
        System.out.println( border );

        // Calculate spaces needed to center the message
        int spacesBeforeMessage = ( borderWidth - msg.length() ) / 2;
        String spaces = " ".repeat( spacesBeforeMessage );

        // Print the centered message with stars
        System.out.println( "***" + spaces + msg + spaces + "***" );

        // Print the bottom border
        System.out.println( border );
    }
    public static double getAverage(double values[]) {
        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }
        return values.length > 0 ? sum / values.length : 0; // Return average or 0 if empty
    }
}

