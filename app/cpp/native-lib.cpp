#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_shijo_newsapp_utils_NativeLib_getApiKey(JNIEnv *env, jobject /* this */) {
    std::string apiKey = "YOUR_API_KEY_HERE"; // Replace with your actual API key
    return env->NewStringUTF(apiKey.c_str());
}
