package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.model.LocalTask;

import java.util.ArrayList;
import java.util.List;

public class LocalTaskServiceImpl implements LocalTaskService {
    private static final List<LocalTask> tasks = new ArrayList<>();

    @Override
    public boolean createTask(LocalTask newTask) {
        return tasks.add(newTask);
    }

    @Override
    public long countTasks() {
        return tasks.size();
    }
}
