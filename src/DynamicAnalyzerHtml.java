import org.antlr.v4.runtime.TokenStreamRewriter;
import java.io.*;

public class DynamicAnalyzerHtml extends dynamicAnalyzerBaseListener {
    /* instantiating object from the test class to be able reach the static variable rewriter */
    test t = new test();
    int i = 0;
    //int[] visited = {0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


    @Override
    public void enterCompoundStatement(dynamicAnalyzerParser.CompoundStatementContext ctx) {

        /* inject html header at the beginning of the html output*/
        if (i == 0) {
            t.htmlRewriter.insertAfter(-1, "<!DOCTYPE html>\n <html lang=\"en\">\n " +
                    "<head> \n<meta charset=\"UTF-8\">\n <title>Dynamic Analyzer</title>\n</head>" +
                    "\n<body> <div style=\"background-color:rgb(229, 255, 236); outline: 1px dotted transparent;\">\n");
            int htmlIndexEnd = ctx.getStop().getTokenIndex();
            t.htmlRewriter.insertBefore(htmlIndexEnd + 1, "</div> \n</body>\n" + "</html>");
        }

        i++;

        if (t.branchCoverageArray[i] == 1) {
            int htmlIndex = ctx.getStart().getTokenIndex();
            t.htmlRewriter.insertBefore(htmlIndex, "<div style=\"background-color:rgb(229, 255, 236);" +
                    "outline: 1px dotted transparent; \">\n");
        }
        if (t.branchCoverageArray[i] == 0) {
            int indexEnd = ctx.getStart().getTokenIndex();
            t.htmlRewriter.insertBefore(indexEnd, "<div style=\"background-color:rgb(255, 238, 240);" +
                    " outline: 1px dotted transparent; \">\n");
        }
    }

    @Override public void exitCompoundStatement(dynamicAnalyzerParser.CompoundStatementContext ctx) {

    }
}
