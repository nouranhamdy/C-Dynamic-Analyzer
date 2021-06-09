# Dynamic-Analyzer
C-Dynamic analyzer that injects test code inside functions to generate statements and branches coverage reports.
# Idea
Inject the source code an array of integers initialized with zeros, and each CompoundStatement, i.e. after '{', is to be injected an array element assigned 1, then outstream the buffer of tokens to output.c file. On running that output file, the array will indicate the covered branches. <br/>
![Analyzer_Diagram](https://user-images.githubusercontent.com/48333642/121285575-eda15400-c8de-11eb-9d5c-768729628500.png)
# steps
Using TokenStreamRewriter(TokenStream tokens) class to insert tokens to the parsing buffer on runtime.
1. in test.java: <br />
  1.1. generate static variable in the test class to be shared among other classes.  <br />
	 <br />  
2. in the class extending from the BaseListener:  <br />
  2.1. declare an integer, to work as array index, that will be incremented for each enterCompoundStatement function call.  <br />
  2.2. get the index of start token in the current sub-parseTree. <br />
  2.3. inject an array element at that index. <br />
<br/>  
3. run the edited code and pipe the output into tex file.<br/>
<br/>
4. read the text file to check the array elements, and using the aforementioned injecting steps, inject html code into the input source code.<br/>
<br/>
# Analyzed code  
![analyzedC-code](https://user-images.githubusercontent.com/48333642/121283397-4ec72880-c8db-11eb-9ec8-7acdb7383d05.PNG)
