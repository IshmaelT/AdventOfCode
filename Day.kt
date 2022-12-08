@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Day {
    @Target(AnnotationTarget.FUNCTION)
    annotation class Part
}
