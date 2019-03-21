package AST.Expression;

import AST.Basic.ExprNode;
import AST.Basic.Type;

import java.util.ArrayList;

public class ArrayExprNode extends ExprNode {
    ExprNode name;
    int dim;
    ArrayList<ExprNode> param;

    public ArrayExprNode(int d) {
        dim = d;
        param = new ArrayList<>();
    }

    public int getDim() { return dim; }

    public Type getNameType() { return name.getType(); }

    public void addExpr(ExprNode e) {
        if (name == null)
            name = e;
        else
            param.add(e);
    }

    @Override
    public boolean isLeftValue() {
        return true;
    }

    @Override
    public void dump(int indent) {
        name.dump(indent);
        for (int i = 0; i < param.size(); ++i)
            param.get(i).dump(indent + 4);
    }
}
