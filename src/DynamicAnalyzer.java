import org.antlr.v4.runtime.TokenStreamRewriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.*;


public class DynamicAnalyzer extends dynamicAnalyzerBaseListener {
    /* instantiating object from the test class to be able reach the static variable rewriter */
    test t = new test();
    File outputFile = new File("src/output.c");
    PrintWriter outFS;
    int i=0;
    int []visited= {0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    {
        try {
            outFS = new PrintWriter(outputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override public void enterCompoundStatement(dynamicAnalyzerParser.CompoundStatementContext ctx) {
        /* inject array declaration at the 1st compoundStatement entrance at the beginning of the parseTree */
        if(i==0) {
            t.rewriter.insertAfter(-1 , "#include <stdio.h>\nint visited [20] = {0} ;\n");
            int indexEnd = ctx.getStop().getTokenIndex();
            t.rewriter.insertAfter(indexEnd - 4 , "\nfor(int i=0 ; i< 20; i++){\nprintf(\"%d \", visited[i]);\n}");
        }
        i++;
        /* get the index of the last token in the parseTree */
        int index1 = ctx.getStart().getTokenIndex();
        /* use index - 1 to inject the line of code before '{' i.e. the last token */
        t.rewriter.insertAfter(index1  , "\nvisited [ " + i + " ] = 1 ;");
        //System.out.println(t.rewriter.getText());


    }

    @Override public void exitCompoundStatement(dynamicAnalyzerParser.CompoundStatementContext ctx) {

    }

    @Override public void enterBlockItemList(dynamicAnalyzerParser.BlockItemListContext ctx) { }

    @Override public void exitBlockItemList(dynamicAnalyzerParser.BlockItemListContext ctx) { }

    @Override public void enterBlockItem(dynamicAnalyzerParser.BlockItemContext ctx) { }

    @Override public void exitBlockItem(dynamicAnalyzerParser.BlockItemContext ctx) { }

    @Override public void enterLabeledStatement(dynamicAnalyzerParser.LabeledStatementContext ctx) { }

    @Override public void exitLabeledStatement(dynamicAnalyzerParser.LabeledStatementContext ctx) { }

    @Override public void enterIterationStatement(dynamicAnalyzerParser.IterationStatementContext ctx) {
//        /* get the index of the last token in the parseTree */
//        int index1 = ctx.getStop().getTokenIndex();
//        /* use index - 1 to inject the line of code before '{' i.e. the last token */
//        t.rewriter.insertAfter(index1 - 1 , "\n visited [ 1 ] = 1 ; \n");
//        System.out.println(t.rewriter.getText());
    }

    @Override public void exitIterationStatement(dynamicAnalyzerParser.IterationStatementContext ctx) { }

    @Override public void enterIfStatement(dynamicAnalyzerParser.IfStatementContext ctx) {
//        int index = ctx.getStop().getTokenIndex();
//        t.rewriter.insertAfter(index -1 , " \n visited [ 1 ] = 1 ; \n");
//        System.out.println(t.rewriter.getText());
    }

}


/*
1- in test.java: generate static variable in the test class to be shared among other classes.
	public static TokenStreamRewriter rewriter;

2- label each selectionStatement production.

3- in the class extending from the BaseListener for each productionListener:
 -> get the sub-parseTree of the ifStatement,then find the index of the last terminal (token\leaf) in the tree
 -> for the compoundStatementListener we inject at the beginning of the parseTree
	test t = new test();
	int index1 = ctx.getStop().getTokenIndex();
        t.rewriter.insertAfter(index1 - 1 , "\n visited [ 1 ] = 1 ; \n");
        System.out.println(t.rewriter.getText());

 */