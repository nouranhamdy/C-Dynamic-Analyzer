# Dynamic-Analyzer
C-Dynamic analyzer that injects test code inside functions to generate statements and branches coverage reports.
# Idea
1. Analyze the source code and stream the tokens buffer into Analyzer 1 to inject C code.
2. Run the injected code to report statement and branch coverage.
3. Redirect the run output into a text file, then read the coverage report from that file.
4. With the aid of that report, inject html code to the source input, such that the covered branches to be highlighted in green and others in red.
![Analyzer_Diagram](https://user-images.githubusercontent.com/48333642/121285575-eda15400-c8de-11eb-9d5c-768729628500.png)
# steps
Using TokenStreamRewriter(TokenStream tokens) class to insert tokens to the parsing buffer on runtime.
1. in test.java: <br />
  1.1. generate static tokenStreamRewriter instance  in the test class to be shared among other classes.  <br />
	 <br />  
2. in the class extending from the BaseListener:  <br />
  2.1. declare an integer, to work as the array index, that will be incremented for each enterCompoundStatement function call.  <br />
  2.2. get the index of start token in the current sub-parseTree. <br />
  2.3. inject an array element at that index. <br />
         <br/>  
3. for automation: <br/>
  3.1. execute cmd commands in java to run the edited code and pipe the output into tex file.<br/>
  3.2. read the text file to check the array elements and, using the aforementioned injecting steps, inject html code into the input source code.<br/>
# Analyzed code  

![analyzedC-code](https://user-images.githubusercontent.com/48333642/121285765-322cef80-c8df-11eb-8594-b0d7c6c3a285.PNG)
