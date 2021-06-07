import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import java.util.Scanner;
import java.io.*;



public class test {
    public static TokenStreamRewriter rewriter;
    public static void main(String [] args) throws Exception {
        String inputFile = "input.c";
        FileInputStream is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        dynamicAnalyzerLexer lexer = new dynamicAnalyzerLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        rewriter = new TokenStreamRewriter(tokens);
        dynamicAnalyzerParser parser = new dynamicAnalyzerParser(tokens);
        ParseTree tree = parser.compilationUnit();
        //DynamicAnalyzer DA = new DynamicAnalyzer();
        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        parseTreeWalker.walk(new DynamicAnalyzer(), tree);
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

//        File inFile = new File ("input.c");
//        File outputFile = new File("src/output.c");
//        Scanner inFS = new Scanner(inFile);
//        PrintWriter outFS = new PrintWriter(outputFile);
//        String line;
//        while (inFS.hasNextLine())
//        {
//            // Read in the line and convert it to upper case
//            line = inFS.nextLine().toUpperCase();
//
//            // Print the result to the console
//            System.out.println(line);
//
//            // Write the read in line to the output file with a new line character
//            outFS.print(line + "\n");
//        }
//
//        // Close any resources used
//        outFS.close();
//        inFS.close();
//    }
    }
}


/*
1- in test.java: generate static variable in the test class to be shared among other classes.
	public static TokenStreamRewriter rewriter;

2- label each selectionStatement production.

3- in the class extending from the BaseListener for each productionListener:
   get the sub-parseTree of the ifStatement,then find the index of the last terminal (token\leaf) in the tree
	test t = new test();
	int index1 = ctx.getStop().getTokenIndex();
        t.rewriter.insertAfter(index1 - 1 , "\n visited [ 1 ] = 1 ; \n");
        System.out.println(t.rewriter.getText());

 */