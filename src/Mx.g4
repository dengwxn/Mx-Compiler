grammar Mx;

// Rules
program
    : declaration* EOF
    ;

declaration
    : functionDeclaration
    | variableDeclaration
    | classDeclaration
    ;

functionDeclaration
    : dataType Identifier?
      '(' (dataType Identifier (',' dataType Identifier)*)? ')'
      blockStatement
    ;

variableDeclaration
    : dataType Identifier ('=' expression)? ';'
    ;

classDeclaration
    : CLASS Identifier '{' (variableDeclaration | functionDeclaration)* '}'
    ;

blockStatement
    : '{' statement* '}'
    ;

statement
    : blockStatement
    | expressionStatement
    | variableDeclaration
    | ifStatement
    | loopStatement
    | branchStatement
    ;

expressionStatement
    : expression? ';'
    ;

expression
    : constant                                                # ConstantLiteral
    | NEW dataType ('[' expression? ']')+                     # NewArray
    | NEW dataType ('(' ')')?                                 # NewType
    | expression ('[' expression ']')+                        # Array
    | Identifier                                              # Identifier
    | expression '(' (expression (',' expression)*)? ')'      # FunctionCall
    | expression '.' Identifier                               # Member
    | '(' expression ')'                                      # SubExpr
    | expression op=('++'|'--')                               # Suffix
    | <assoc=right> op=('+'|'-'|'~'|'!'|'++'|'--') expression # Prefix
    | expression op=('*'|'/'|'%') expression                  # MulDivMod
    | expression op=('+'|'-') expression                      # AddSub
    | expression op=('<<'|'>>') expression                    # Shift
    | expression op=('<'|'<='|'>'|'>=') expression            # Compare
    | expression op=('=='|'!=') expression                    # Equal
    | expression op='&' expression                            # And
    | expression op='^' expression                            # Xor
    | expression op='|' expression                            # Or
    | expression op='&&' expression                           # LogicAnd
    | expression op='||' expression                           # LogicOr
    | expression op='=' expression                            # Assign
    ;

ifStatement
    : IF '(' expression ')' statement (ELSE statement)?
    ;

loopStatement
    : FOR '(' expression? ';' expression? ';' expression? ')' statement # For
    | WHILE '(' expression ')' statement                                # While
    ;

branchStatement
    : RETURN expression? ';' # Return
    | BREAK ';'              # Break
    | CONTINUE ';'           # Continue
    ;

dataType
    : INT              # IntType
    | BOOL             # BoolType
    | VOID             # VoidType
    | dataType '[' ']' # ArrayType
    | Identifier       # ClassType
    | STRING           # StringType
    ;

constant
    : NULL           # Null
    | (TRUE | FALSE) # Bool
    | (Number)       # Number
    | StringLiteral  # String
    ;

// Tokens
StringLiteral: '"' ('\\' [btnr"\\] | .)*? '"';
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
MOD: '%';

LESS: '<';
LESSEQUAL: '<=';
GREATER: '>';
GREATEREQUAL: '>=';
EQUAL: '==';
NOTEQUAL: '!=';

LOGICAND: '&&';
LOGICOR: '||';
LOGICNOT: '!';

AND: '&';
OR: '|';
NOT: '~';
XOR: '^';
LEFTSHIFT: '<<';
RIGHTSHIFT: '>>';

ASSIGN: '=';

UNARYADD: '++';
UNARYSUB: '--';

SEMICOLON: ';';
COMMA: ',';
DOT: '.';

LEFTPAREN: '(';
RIGHTPAREN: ')';
LEFTBRACKET: '[';
RIGHTBRACKET: ']';
LEFTBRACE: '{';
RIGHTBRACE: '}';

WHITESPACE: [ \r\n\t]+ -> skip;
LINECOMMENT: '//' (~[\n\r])* -> skip;
BLOCKCOMMENT : '/*' .*? '*/' -> skip;

BOOL: 'bool';
INT: 'int';
STRING: 'string';
NULL: 'null';
VOID: 'void';
TRUE: 'true';
FALSE: 'false';
IF: 'if';
ELSE: 'else';
FOR: 'for';
WHILE: 'while';
BREAK: 'break';
CONTINUE: 'continue';
RETURN: 'return';
NEW: 'new';
CLASS: 'class';

Number: [0-9]+;
Identifier: [a-zA-Z][_a-zA-Z0-9]*;