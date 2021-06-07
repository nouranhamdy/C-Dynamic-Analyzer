import org.antlr.v4.runtime.TokenStreamRewriter;

public class DynamicVisitor extends dynamicAnalyzerBaseVisitor<Integer>{
    @Override public Integer visitCompoundStatement(dynamicAnalyzerParser.CompoundStatementContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Integer visitBlockItemList(dynamicAnalyzerParser.BlockItemListContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Integer visitBlockItem(dynamicAnalyzerParser.BlockItemContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
 //     'if' '(' expression ')' statement ('else' statement)?
    @Override public Integer visitIfStatement(dynamicAnalyzerParser.IfStatementContext ctx) {
//        String stmt = ctx.statement(0).getText();
//        Integer flag = visit(ctx.statement(2).getToken())
//        if (stmt.equals("visited[1] = 1 ;")){
//
//        }
//        test t = new test();
//        t.rewriter.insertAfter(10 , "visited [ 1 ] = 1 ;");
//        System.out.println(t.rewriter.getText());
        return 0;
    }

    @Override public Integer visitLabeledStatement(dynamicAnalyzerParser.LabeledStatementContext ctx) {
        return 0;
         }

}
