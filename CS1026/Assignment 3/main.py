def main():
    # imports the program sentiment_analysis
    import sentiment_analysis

    ### takes input for the tweets file and the keywords file
    tweetsname = input('Please enter the name of the file containing tweets including the file extension: ')

    keysname = input('Please enter the name of the file containing keywords including the file extension: ')

    ### calls the sentiment_analysis function compute_tweets to calculate the sentiment with the given files
    sentiment = sentiment_analysis.compute_tweets(tweetsname,keysname)

    ### checks to see if the file existed
    if sentiment != []:
        ### checks to make sure there is data for each timezone and prints the results accordingly
        if sentiment[0][1] != 0:
            print('The avergae sentiment for the eastern time zone is:',sentiment[0][0],'with', sentiment[0][1], 'tweets',sep=' ')
        else:
            print('There were no tweets containing keywords for the eastern time zone')

        if sentiment[1][1] != 0:
            print('The average sentiment for the central time zone is:',sentiment[1][0],'with', sentiment[1][1], 'tweets',sep=' ')
        else:
            print('There were no tweets containing keywords for the central time zone')

        if sentiment[2][1] != 0:
            print('The average sentiment for the mountain time zone is:',sentiment[2][0],'with', sentiment[2][1], 'tweets',sep=' ')
        else:
            print('There were no tweets containing keywords for the mountain time zone')

        if sentiment[3][1] != 0:
            print('The average sentiment for the pacific time zone is:',sentiment[3][0],'with', sentiment[3][1], 'tweets',sep=' ')
        else:
            print('There were no tweets containing keywords for the pacific time zone')

    else:
        print('one or more file errors, please re input files and be sure to include file extension')


### calls the main function to initialize the program
main()
