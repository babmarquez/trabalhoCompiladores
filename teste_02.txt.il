.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module _codigo_objeto.exe

.class public _UNICA{
.method static public void _principal() { 
  .entrypoint
.locals(float64 lado)
.locals(float64 area)
ldstr "digite um valor para lado: "
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine()
call float64 [mscorlib]System.Double::Parse(string)
stloc lado
ldloc lado
ldloc lado
mul
stloc area
ldloc area
call void [mscorlib]System.Console::Write(float64)
 ret
 }
}