.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module _codigo_objeto.exe

.class public _UNICA{
.method static public void _principal() { 
  ntrypoint
ldc.i8 1
conv.r8
conv.i8
call void [mscorlib]System.Console::Write(int64)
ldstr " "
call void [mscorlib]System.Console::Write(string)
ldc.r8 1.5
call void [mscorlib]System.Console::Write(float64)
ldstr " ---> olá mundos!"
call void [mscorlib]System.Console::Write(string)
ldstr "\n"
call void [mscorlib]System.Console::Write(string)
 ret
 }
}