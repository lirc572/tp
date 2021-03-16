package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteContactFromCommand.MESSAGE_DELETE_PROJECT_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProjects.getTypicalProjectsFolder;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.testutil.PersonBuilder;

public class DeleteContactFromCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalProjectsFolder(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = new PersonBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());

        model.setProject(
                projectToEdit,
                projectToEdit.addParticipant(personToDelete)
        );

        Index lastContactIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getParticipants().size());

        DeleteContactFromCommand deleteContactFromCommand = new DeleteContactFromCommand(INDEX_FIRST, lastContactIndex);

        String expectedMessage = String.format(MESSAGE_DELETE_PROJECT_SUCCESS,
                personToDelete.getName(), projectToEdit.getProjectName());

        ModelManager expectedModel = new ModelManager(
                getTypicalAddressBook(), getTypicalProjectsFolder(), new UserPrefs()
        );

        assertCommandSuccess(deleteContactFromCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Person personToDelete = new PersonBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());

        model.setProject(
                projectToEdit,
                projectToEdit.addParticipant(personToDelete)
        );

        Index lastContactIndex = Index.fromOneBased(model.getFilteredProjectList().get(
                INDEX_FIRST.getZeroBased()).getParticipants().size());

        DeleteContactFromCommand deleteContactFromCommand = new DeleteContactFromCommand(INDEX_THIRD, lastContactIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> deleteContactFromCommand.execute(model)
        );
    }

    @Test
    public void equals() {
        Person personToDelete = new PersonBuilder().build();
        Project project1ToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());

        model.setProject(
                project1ToEdit,
                project1ToEdit.addParticipant(personToDelete)
        );

        Project project2ToEdit = model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased());

        model.setProject(
                project2ToEdit,
                project2ToEdit.addParticipant(personToDelete)
        );

        Index lastContactFrom1Index = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getParticipants().size());
        Index lastContactFrom2Index = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getParticipants().size());

        DeleteContactFromCommand deleteContactFrom1Command = new DeleteContactFromCommand(
                INDEX_FIRST, lastContactFrom1Index);
        DeleteContactFromCommand deleteContactFrom2Command = new DeleteContactFromCommand(
                INDEX_SECOND, lastContactFrom2Index);

        // same object -> returns true
        assertEquals(deleteContactFrom1Command, deleteContactFrom1Command);

        // same values -> returns true
        DeleteContactFromCommand deleteContactFrom1CommandCopy = new DeleteContactFromCommand(
                INDEX_FIRST, lastContactFrom1Index);
        assertEquals(deleteContactFrom1Command, deleteContactFrom1CommandCopy);

        // different types -> returns false
        assertNotEquals(deleteContactFrom1Command, 1);

        // null -> returns false
        assertNotEquals(deleteContactFrom1Command, null);

        // different person -> returns false
        assertNotEquals(deleteContactFrom1Command, deleteContactFrom2Command);
    }

}