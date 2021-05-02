package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.model.LocalTask;

/**
 * Define a set of common tests for all types of {@link LocalTaskService}s.
 */
public abstract class LocalTaskServiceTest extends AbstractTaskServiceTest<LocalTask> {
    public LocalTaskServiceTest() {
        super(LocalTask::new);
    }

    @Override
    protected long getBaseCount() {
        return 0;
    }
}
