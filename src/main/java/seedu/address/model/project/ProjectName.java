package seedu.address.model.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Project's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidProjectName(String)}
 */
public class ProjectName {

    public static final String MESSAGE_CONSTRAINTS =
            "Project name should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String projectName;

    /**
     * Constructs a {@code ProjectName}.
     *
     * @param name A valid project name.
     */
    public ProjectName(String name) {
        requireNonNull(name);

        checkArgument(isValidProjectName(name), MESSAGE_CONSTRAINTS);
        projectName = name;
    }

    /**
     * Returns true if a given string is a valid project name.
     */
    public static boolean isValidProjectName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return projectName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProjectName // instanceof handles nulls
                && projectName.equals(((ProjectName) other).projectName)); // state check
    }

    @Override
    public int hashCode() {
        return projectName.hashCode();
    }

}
