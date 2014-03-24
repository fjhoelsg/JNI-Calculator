#include "jni_calc.h"
#include <math.h>
/*
 * Class:     cr_ac_tec_appsmoviles_jnicalculator_calculator_processing_NativeArithmeticProcessor
 * Method:    nativeAdd
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_cr_ac_tec_appsmoviles_jnicalculator_calculator_processing_NativeArithmeticProcessor_nativeAdd(JNIEnv *env, jobject obj, jdouble augend, jdouble addend) {
  return augend + addend;
}

/*
 * Class:     cr_ac_tec_appsmoviles_jnicalculator_calculator_processing_NativeArithmeticProcessor
 * Method:    nativeDivide
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_cr_ac_tec_appsmoviles_jnicalculator_calculator_processing_NativeArithmeticProcessor_nativeDivide(JNIEnv *env, jobject obj, jdouble dividend, jdouble divisor) {
  return dividend / divisor;
}

/*
 * Class:     cr_ac_tec_appsmoviles_jnicalculator_calculator_processing_NativeArithmeticProcessor
 * Method:    nativeExponentiate
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_cr_ac_tec_appsmoviles_jnicalculator_calculator_processing_NativeArithmeticProcessor_nativeExponentiate(JNIEnv *env, jobject obj, jdouble base, jdouble exponent) {
  return pow(base, exponent);
}

/*
 * Class:     cr_ac_tec_appsmoviles_jnicalculator_calculator_processing_NativeArithmeticProcessor
 * Method:    nativeMultiply
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_cr_ac_tec_appsmoviles_jnicalculator_calculator_processing_NativeArithmeticProcessor_nativeMultiply(JNIEnv *env, jobject obj, jdouble multiplicand, jdouble multiplier) {
  return multiplicand * multiplier;
}



/*
 * Class:     cr_ac_tec_appsmoviles_jnicalculator_calculator_processing_NativeArithmeticProcessor
 * Method:    nativeSubtract
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_cr_ac_tec_appsmoviles_jnicalculator_calculator_processing_NativeArithmeticProcessor_nativeSubtract (JNIEnv *env, jobject obj, jdouble minuend, jdouble subtrahend) {
  return minuend - subtrahend;
}
