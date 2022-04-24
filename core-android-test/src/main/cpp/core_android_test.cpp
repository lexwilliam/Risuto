#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_lexwilliam_core_1android_1test_NativeLib_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}