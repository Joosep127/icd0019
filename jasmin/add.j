.class public Add
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
   .limit stack 3
   getstatic java/lang/System/out Ljava/io/PrintStream;
   ldc 3
   ldc 4
   iadd
   invokevirtual java/io/PrintStream/println(I)V
   return
.end method