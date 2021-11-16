package Service;

public interface ClientAble {
    /**
     * Creates a request from the given resource
     * @param resource the url:. to the data source which the request is being sent to
     * @return the body response as text.
     */
    String get(String resource) throws Exception;
}
