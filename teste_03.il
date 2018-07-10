.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module _codigo_objeto.exe

.class public _UNICA{
.method static public void _principal() { 
  .entrypoint
.locals(float64 lado)
.locals(float64 area)
call string [mscorlib]System.Console::ReadLine()
call float64 [mscorlib]System.Double::Parse(string)
stloc lado
label1:
ldloc lado
ldc.i8 0
conv.r8
cgt
brfalse label2
ldloc lado
ldloc lado
mul
stloc area
br label3
label2:
ldstr "erro: valor inválido para lado - "
call void [mscorlib]System.Console::Write(string)
ldc.r8 0.0
stloc area
label3:
ldstr "área: "
call void [mscorlib]System.Console::Write(string)
ldloc area
call void [mscorlib]System.Console::Write(float64)
 ret
 }
}