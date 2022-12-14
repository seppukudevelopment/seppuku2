package pw.seppuku.plugin.mixin;

public interface ActualThis<T> {

    @SuppressWarnings("unchecked")
    default T actualThis() {
        return (T) this;
    }
}
