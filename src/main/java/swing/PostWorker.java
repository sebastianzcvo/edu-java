package swing;

import javax.swing.*;
import java.util.function.Consumer;

public class PostWorker extends SwingWorker<String, Void> {

    private final int postId;
    private final PostService postService;
    private final Consumer<String> onSuccess;
    private final Consumer<Exception> onError;
    private final Runnable onComplete;

    public PostWorker(int postId, Consumer<String> onSuccess, Consumer<Exception> onError, Runnable onComplete) {
        this.postService = new PostService();
        this.postId = postId;
        this.onSuccess = onSuccess;
        this.onError = onError;
        this.onComplete = onComplete;
    }

    @Override
    protected String doInBackground() throws Exception {
        return postService.fetchPostTitle(postId);
    }

    @Override
    protected void done() {
        try {
            String result = get();
            onSuccess.accept(result);
        } catch (Exception ex) {
            onError.accept(ex);
        } finally {
            onComplete.run();
        }
    }
}
