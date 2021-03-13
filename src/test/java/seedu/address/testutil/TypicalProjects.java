package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ProjectsFolder;
import seedu.address.model.project.CompletableTaskList;
import seedu.address.model.project.EventList;
import seedu.address.model.project.ParticipantList;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

/**
 * A utility class containing a list of {@code Project} objects to be used in tests.
 */
public class TypicalProjects {

    public static final EventList EMPTY_EVENT_LIST = new EventList();
    public static final CompletableTaskList EMPTY_COMPLETABLE_TASK_LIST = new CompletableTaskList();
    public static final ParticipantList EMPTY_PARTICIPANT_LIST = new ParticipantList();

    public static final ProjectName CS2103T_PROJECT_NAME = new ProjectName("CS2103T Project");
    public static final Project CS2103T_PROJECT = new Project(CS2103T_PROJECT_NAME);

    public static final ProjectName CS1101S_NAME = new ProjectName("CS1101S");
    public static final Project CS1101S = new Project(CS1101S_NAME);

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns a {@code ProjectsFolder} with all the typical projects.
     */
    public static ProjectsFolder getTypicalProjectsFolder() {
        ProjectsFolder pf = new ProjectsFolder();
        for (Project project : getTypicalProjects()) {
            pf.addProject(project);
        }
        return pf;
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(CS2103T_PROJECT, CS1101S));
    }
}