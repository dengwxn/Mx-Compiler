package AST.Expression;

import java.util.ArrayList;

public class NewArrayExprNode extends ExprNode {
    String base;
    int dim;
    ArrayList<ExprNode> param;

    public NewArrayExprNode(String base, int dim) {
        this.base = base;
        this.dim = dim;
        this.param = new ArrayList<>();
    }

    public int getDim() {
        return dim;
    }

    public void addParam(ExprNode p) {
        param.add(p);
    }

    public String getBase() {
        return base;
    }

    @Override
    public boolean isLeftValue() {
        return false;
    }

    @Override
    public void dump(int indent) {
        format(indent);
        System.out.printf("new %s:\n", base);
        for (ExprNode expr : param) {
            if (expr != null)
                expr.dump(indent + 4);
            else {
                format(indent + 4);
                System.out.println("null");
            }
        }
    }
}
