package com.huige.sam;


import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

public class TestMethodVisitor extends AdviceAdapter {
    private String className;
    private String superName;

    protected TestMethodVisitor(int i, MethodVisitor methodVisitor, int i1, String s, String s1, String className, String superName) {
        super(i, methodVisitor, i1, s, s1);
        this.className = className;
        this.superName = superName;
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        mv.visitVarInsn(LSTORE, 2);
        mv.visitLdcInsn("TAG");
        mv.visitVarInsn(LLOAD, 2);
        mv.visitInvokeDynamicInsn("makeConcatWithConstants", "(J)Ljava/lang/String;", new Handle(Opcodes.H_INVOKESTATIC, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false), new Object[]{"time=\u0001"});
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitLdcInsn("TAG");
        mv.visitLdcInsn(className + "---->" + superName);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);
    }

    @Override
    protected void onMethodExit(int opcode) {
        mv.visitLdcInsn("TAG");
        mv.visitLdcInsn("this is end");
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);
        super.onMethodExit(opcode);
    }
}
