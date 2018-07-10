.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module _codigo_objeto.exe

.class public _UNICA{
.method static public void _principal() { 
  .entrypoint
.locals(int64 CH)
ldstr "qual a CH de compiladores? "
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc CH
label1:
ldloc CH
conv.r8
ldc.i8 18
conv.r8
clt
brfalse label2
ldstr "qual a CH de compiladores? "
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc CH
br label1
label2:
ldstr "total de créditos: "
call void [mscorlib]System.Console::Write(string)
ldloc CH
conv.r8
ldc.i8 18
conv.r8
div
conv.i8
call void [mscorlib]System.Console::Write(int64)
 ret
 }
}