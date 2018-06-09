package trabalhofinal;

public interface ParserConstants
{
    int START_SYMBOL = 45;

    int FIRST_NON_TERMINAL    = 45;
    int FIRST_SEMANTIC_ACTION = 91;

    int[][] PARSER_TABLE =
    {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  2, -1, -1,  2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  1,  2, -1, -1 },
        { -1,  3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  5, -1, -1,  5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  5, -1, -1 },
        { -1, -1, -1, -1, -1,  7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  6, -1, -1, -1, -1, -1,  6, -1, -1, -1, -1,  6, -1, -1, -1,  6, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1, -1, -1, -1,  9, -1, -1, -1, -1,  8, -1, -1, -1, 10, -1, -1, -1, -1, -1 },
        { -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 13, -1, -1, -1, -1, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1, -1 },
        { -1, 18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1, -1 },
        { -1, -1, 21, 22, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 24, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 27, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, -1, -1 },
        { -1, 28, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 34, -1, -1, -1, -1, -1, 32, -1, -1, -1, -1, 31, -1, -1, -1, 33, -1, -1, -1, -1, -1 },
        { -1, 36, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, 38, 38, -1, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 39, -1, -1, -1, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1, 39, 39, -1, -1, -1, -1, -1, -1, -1 },
        { 41, 40, -1, -1, -1, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 41, -1, -1, -1, -1, 41, -1, 40, -1, 40, 40, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 42, -1, -1, -1, 45, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, 44, 44, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, 46, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 47, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 48, 48, -1, -1, -1, -1, -1, -1, -1, -1, -1, 49, 49 },
        { -1, -1, -1, -1, -1, -1, -1, -1, 50, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 51, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 52, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 53, 54, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 55, 55, 55, 55, 55, -1, -1, -1, -1, -1, -1, 55, -1, -1, -1, -1, -1, 55, 55, -1, -1, -1, -1, -1, -1, -1, -1, 55, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 55, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, 57, -1, -1, 56, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 59, 58, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, -1, -1, -1, -1, 61, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, 62 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, 63 },
        { -1, 65, 65, 65, 65, 65, -1, -1, -1, -1, -1, -1, 65, -1, -1, -1, -1, -1, 65, 65, -1, -1, -1, -1, -1, -1, -1, -1, 65, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 65, -1, -1, -1, -1 },
        { 68, 68, 68, 68, 68, 68, 68, -1, -1, 68, 66, 67, 68, -1, -1, -1, -1, -1, 68, 68, -1, -1, -1, -1, -1, -1, 68, -1, 68, -1, -1, 68, -1, 68, -1, 68, 68, -1, -1, 68, -1, -1, -1, -1 },
        { -1, 69, 69, 69, 69, 69, -1, -1, -1, -1, -1, -1, 72, -1, -1, -1, -1, -1, 69, 69, -1, -1, -1, -1, -1, -1, -1, -1, 71, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 70, -1, -1, -1, -1 },
        { -1, 73, 73, 73, 73, 73, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 73, 73, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { 75, 75, 75, 75, 75, 75, 75, 74, -1, 75, 75, 75, 75, 74, 74, 74, 74, 74, 75, 75, -1, -1, -1, -1, -1, -1, 75, -1, 75, -1, -1, 75, -1, 75, -1, 75, 75, -1, -1, 75, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, 76, -1, -1, -1, -1, -1, 77, 78, 79, 80, 81, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 82, 82, 82, 82, 82, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 82, 82, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { 85, 85, 85, 85, 85, 85, 85, 85, -1, 85, 85, 85, 85, 85, 85, 85, 85, 85, 83, 84, -1, -1, -1, -1, -1, -1, 85, -1, 85, -1, -1, 85, -1, 85, -1, 85, 85, -1, -1, 85, -1, -1, -1, -1 },
        { -1, 86, 86, 86, 86, 86, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 86, 86, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { 89, 89, 89, 89, 89, 89, 89, 89, -1, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 87, 88, -1, -1, -1, -1, 89, -1, 89, -1, -1, 89, -1, 89, -1, 89, 89, -1, -1, 89, -1, -1, -1, -1 },
        { -1, 90, 91, 92, 93, 94, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 95, 96, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { 98, 98, 98, 98, 98, 98, 98, 98, -1, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, -1, -1, -1, -1, 98, -1, 98, -1, 97, 98, -1, 98, -1, 98, 98, -1, -1, 98, -1, -1, -1, -1 }
    };

    int[][] PRODUCTIONS = 
    {
        { 26, 46, 54, 58, 28, 64 },
        { 41, 47 },
        {  0 },
        {  2, 23, 49, 48 },
        { 47 },
        {  0 },
        { 50 },
        {  6, 51,  7 },
        { 35 },
        { 30 },
        { 39 },
        { 24 },
        { 53, 52 },
        { 51 },
        {  0 },
        {  2, 23, 50 },
        { 25, 55 },
        {  0 },
        { 62,  8, 57, 56 },
        { 55 },
        {  0 },
        {  3 },
        {  4 },
        {  5 },
        { 40 },
        { 29 },
        { 42, 59 },
        {  0 },
        { 62, 23, 61, 60 },
        { 59 },
        {  0 },
        { 35 },
        { 30 },
        { 39 },
        { 24 },
        {  2 },
        {  2, 63 },
        { 10, 62 },
        {  0 },
        { 66, 65 },
        { 64 },
        {  0 },
        {  2, 67 },
        { 71 },
        { 72 },
        {  6, 79,  7, 68 },
        { 69 },
        { 70 },
        { 75 },
        { 77 },
        {  9, 79 },
        { 38,  6,  2, 10, 79,  7 },
        { 34,  6, 62,  7 },
        { 36,  6, 73,  7 },
        { 37,  6, 73,  7 },
        { 79, 74 },
        { 10, 79, 73 },
        {  0 },
        { 33, 23, 64, 76 },
        { 32, 23, 64, 27 },
        { 27 },
        { 32, 23, 64, 27 },
        { 78, 23, 64, 27 },
        { 44 },
        { 43 },
        { 81, 80 },
        { 11, 81, 80 },
        { 12, 81, 80 },
        {  0 },
        { 82 },
        { 40 },
        { 29 },
        { 13, 81 },
        { 85, 83 },
        { 84, 85 },
        {  0 },
        {  8 },
        { 14 },
        { 15 },
        { 16 },
        { 17 },
        { 18 },
        { 87, 86 },
        { 19, 87, 86 },
        { 20, 87, 86 },
        {  0 },
        { 89, 88 },
        { 21, 89, 88 },
        { 22, 89, 88 },
        {  0 },
        {  2, 90 },
        {  3 },
        {  4 },
        {  5 },
        {  6, 79,  7 },
        { 19, 89 },
        { 20, 89 },
        { 31,  6,  2,  7 },
        {  0 }
    };

    String[] PARSER_ERROR =
    {
        "",
        "esperado fim de programa",
        "esperado identificador",
        "esperado constante int",
        "esperado constante float",
        "esperado constante str",
        "esperado \"(\"",
        "esperado \")\"",
        "esperado \"=\"",
        "esperado \":=\"",
        "esperado \",\"",
        "esperado \"&&\"",
        "esperado \"||\"",
        "esperado \"!\"",
        "esperado \"!=\"",
        "esperado \"<\"",
        "esperado \"<=\"",
        "esperado \">\"",
        "esperado \">=\"",
        "esperado \"+\"",
        "esperado \"-\"",
        "esperado \"*\"",
        "esperado \"/\"",
        "esperado \":\"",
        "esperado BOOL",
        "esperado CONSTS",
        "esperado DEF",
        "esperado END",
        "esperado EXECUTE",
        "esperado FALSE",
        "esperado FLOAT",
        "esperado GET",
        "esperado IFFALSE",
        "esperado IFTRUE",
        "esperado INPUT",
        "esperado INT",
        "esperado PRINT",
        "esperado PRINTLN",
        "esperado SET",
        "esperado STR",
        "esperado TRUE",
        "esperado TYPES",
        "esperado VAR",
        "esperado WHILEFALSE",
        "esperado WHILETRUE",
        "esperado DEF",//"<programa> inv�lido",
        "esperado CONSTS, EXECUTE, TYPES ou VAR",//"<def_tipo> inv�lido",
        "esperado identificador",//"<declara_id> inv�lido",
        "esperado identificador, CONSTS, EXECUTE ou VAR",//"<declara_id1> inv�lido",
        "esperado \"(\", bool, float, int ou str",//"<e_do_tipo> inv�lido",
        "esperado bool, float, int ou str",//"<simples> inv�lido",
        "esperado identificador",//"<lista_de_campos> inv�lido",
        "esperado identificador, \")\"",//"<lista_de_campos1> inv�lido",
        "esperado identificador",//"<campo> inv�lido",
        "esperado CONSTS, EXECUTE ou VAR",//"<def_const> inv�lido",
        "esperado identificador",//"<declara_consts> inv�lido",
        "esperado identificador, EXECUTE, VAR",//"<declara_consts1> inv�lido",
        "esperado constante int, constante float, constante str, FALSE ou TRUE",//"<valor> inv�lido",
        "esperado EXECUTE ou VAR",//"<declara_var> inv�lido",
        "esperado identificador",//"<declara_tipo> inv�lido",
        "esperado identificador ou EXECUTE",//"<declara_tipo1> inv�lido",
        "esperado identificador, bool, floar, int ou str",//"<tipo> inv�lido",
        "esperado identificador",//"<lista_de_identificadores> inv�lido",
        "esperado \")\", \"=\", \",\" ou \":\"",//"<lista_de_identificadores1> inv�lido",
        "esperado identificador, \"(\", INPUT, PRINT ou PRINTLN",//"<lista_comandos> inv�lido",
        "esperado fim de programa, identificador, \"(\", END, IFFALSE, INPUT, PRINT ou PRINTLN",//"<lista_comandos1> inv�lido",
        "esperado comando",//"<comando> inv�lido",
        "esperado \":=\" ou SET",//"<comando1> inv�lido",
        "esperado IFFALSE, IFTRUE, WHILEFALSE ou WHILETRUE",//"<comando2> inv�lido",
        "esperado \":=\"",//"<comand_atribuicao> inv�lido",
        "esperado SET",//"<comand_manipulacao_compostos> inv�lido",
        "esperado INPUT",//"<comand_entrada_dados> inv�lido",
        "esperado PRINT ou PRINTLN",//"<saida_dados> inv�lido",
        "esperado expressão",//"<lista_de_expressoes> inv�lido",
        "esperado expressão",//"<lista_de_expressoes1> inv�lido",
        "esperado expressão",//"<selecao> inv�lido",
        "esperado expressão",//"<selecao1> inv�lido",
        "esperado expressão",//"<repeticao> inv�lido",
        "esperado expressão",//"<condicao_repeticao> inv�lido",
        "esperado expressão",//"<expressao> inv�lido",
        "esperado expressão",//"<expressao1> inv�lido",
        "esperado expressão",//"<elemento> inv�lido",
        "esperado expressão",//"<relacional> inv�lido",
        "esperado expressão",//"<relacional1> inv�lido",
        "esperado expressão",//"<operador_relacional> inv�lido",
        "esperado expressão",//"<aritmetica> inv�lido",
        "esperado expressão",//"<aritmetica1> inv�lido",
        "esperado expressão",//"<termo> inv�lido",
        "esperado expressão",//"<termo1> inv�lido",
        "esperado expressão",//"<fator> inv�lido",
        "esperado expressão"//"<fator1> inv�lido"
    };
}
