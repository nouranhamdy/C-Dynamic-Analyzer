import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import java.util.Scanner;
import java.io.*;



public class test {
    public static TokenStreamRewriter rewriter;
    public static TokenStreamRewriter htmlRewriter;
    public static CommonTokenStream tokens;
    public static int [] branchCoverageArray;
    public static void main(String [] args) throws Exception {
        String inputFile = "input.c";
        FileInputStream is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        dynamicAnalyzerLexer lexer = new dynamicAnalyzerLexer(input);
        tokens = new CommonTokenStream(lexer);
        rewriter = new TokenStreamRewriter(tokens);
        htmlRewriter = new TokenStreamRewriter(tokens);
        dynamicAnalyzerParser parser = new dynamicAnalyzerParser(tokens);
        ParseTree tree = parser.compilationUnit();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(new DynamicAnalyzer(), tree);
        injectC();

        ParseTreeWalker parseTreeWalker2 = new ParseTreeWalker();
        parseTreeWalker2.walk(new DynamicAnalyzerHtml(), tree);
        injectHtml();


    }


    static void injectC() throws IOException {
        /* decorating injections */
        for(int i=0 ; i< tokens.size(); i++){
            rewriter.insertAfter(i, " ");
            String tokenText = tokens.get(i).getText();
            if(tokenText.equals("{") || tokenText.equals("}") || tokenText.equals(";")){
                rewriter.insertAfter(i, "\n");
            }
        }
        File outputFile = new File("src/output.c");
        PrintWriter outFS = new PrintWriter(outputFile);
        outFS.print(rewriter.getText() + "\n");
        outFS.close();
        System.out.println(rewriter.getText());

        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd /d E:\\gcc\\bin" + // change directory to gcc bin
                " && gcc C:\\Users\\noraan\\IdeaProjects\\Dynamic_Analyzer\\src\\output.c -o C:\\Users\\noraan\\IdeaProjects\\Dynamic_Analyzer\\output"+ //compile the output.c file
                "&& C:\\Users\\noraan\\IdeaProjects\\Dynamic_Analyzer\\output > C:\\Users\\noraan\\IdeaProjects\\Dynamic_Analyzer\\src\\outputOfOutput.txt" + //redirect the output of output.c to txt file
                " && del C:\\Users\\noraan\\IdeaProjects\\Dynamic_Analyzer\\output.exe \"");  //delete execution (output.exe) file because it contains the first compilation only i.e. never updates

        BufferedReader input = new BufferedReader(new FileReader("src\\outputOfOutput.txt"));
        String last = " ", line;

        while ((line = input.readLine()) != null) {
            last = line;
        }
        int index = 0 , k = 0;
        branchCoverageArray = new int[last.length()/2];
        while(!last.isEmpty() && index < last.length()-1){
            if(last.charAt(index) != ' ') {
               branchCoverageArray[k] = Integer.parseInt(String.valueOf(last.charAt(index)));
                k++;
            }
            index++;
        }

    }

    static void injectHtml() throws IOException {
        for(int i=0 ; i< tokens.size(); i++){
            htmlRewriter.insertAfter(i, " ");
            String tokenText2 = tokens.get(i).getText();
            if(tokenText2.equals("{") || tokenText2.equals("}") || tokenText2.equals(";")){
                htmlRewriter.insertAfter(i, "<br>\n");
            }
        }

        File outputHtml = new File("src/htmlOutput.html");
        PrintWriter outFS2 = new PrintWriter(outputHtml);
        outFS2.print(htmlRewriter.getText() + "\n");
        outFS2.close();
        //System.out.println(htmlRewriter.getText());

        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd C:\\Users\\noraan\\IdeaProjects\\Dynamic_Analyzer\\src && start chrome htmlOutput.html\"");
    }
}



/*
1- in test.java: generate static variable in the test class to be shared among other classes.
	public static TokenStreamRewriter rewriter;
   write a code the opens terminal and run command
   Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd /d E:\\gcc\\bin ");

2- label each selectionStatement production.

3- in the class extending from the BaseListener for each productionListener:
   get the sub-parseTree of the ifStatement,then find the index of the last terminal (token\leaf) in the tree
	test t = new test();
	int index1 = ctx.getStop().getTokenIndex();
        t.rewriter.insertAfter(index1 - 1 , "\n visited [ 1 ] = 1 ; \n");
        System.out.println(t.rewriter.getText());


        green=rgb(229, 255, 236)
        red=rgb(255, 238, 240);

 */

/*
         //decorating injections
        for(int i=0 ; i< tokens.size(); i++){
            rewriter.insertAfter(i, " ");
            String tokenText = tokens.get(i).getText();
            if(tokenText.equals("{") || tokenText.equals("}") || tokenText.equals(";")){
                rewriter.insertAfter(i, "\n");
            }
        }
        File outputFile = new File("src/output.c");
        PrintWriter outFS = new PrintWriter(outputFile);
        outFS.print(rewriter.getText() + "\n");
        outFS.close();
        System.out.println(rewriter.getText());

        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd /d E:\\gcc\\bin" +
                " && gcc C:\\Users\\noraan\\IdeaProjects\\Dynamic_Analyzer\\src\\output.c -o C:\\Users\\noraan\\IdeaProjects\\"+
                "Dynamic_Analyzer\\output && C:\\Users\\noraan\\IdeaProjects\\Dynamic_Analyzer\\src\\output \"");
*/

/*
        for(int i=0 ; i< tokens.size(); i++){
            htmlRewriter.insertAfter(i, " ");
            String tokenText2 = tokens.get(i).getText();
            if(tokenText2.equals("{") || tokenText2.equals("}") || tokenText2.equals(";")){
                htmlRewriter.insertAfter(i, "<br>\n");
            }
        }

        File outputHtml = new File("src/htmlOutput.html");
        PrintWriter outFS2 = new PrintWriter(outputHtml);
        outFS2.print(htmlRewriter.getText() + "\n");
        outFS2.close();
        //System.out.println(htmlRewriter.getText());

        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd C:\\Users\\noraan\\IdeaProjects\\Dynamic_Analyzer\\src && start chrome htmlOutput.html\"");

*/


/*

        File inFile = new File ("input.c");
        File outputFile = new File("src/output.c");
        Scanner inFS = new Scanner(inFile);
        PrintWriter outFS = new PrintWriter(outputFile);
        String line;
        while (inFS.hasNextLine())
        {
            // Read in the line and convert it to upper case
            line = inFS.nextLine().toUpperCase();

            // Print the result to the console
            System.out.println(line);

            // Write the read in line to the output file with a new line character
            outFS.print(line + "\n");
        }

        // Close any resources used
        outFS.close();
        inFS.close();
    }
*/