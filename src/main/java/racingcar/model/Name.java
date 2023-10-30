package racingcar.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import racingcar.constant.GameConstant;

public class Name {

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateLength(name);
        validateNoWhiteSpace(name);
    }

    private void validateLength(String name) {
        if (name.length() < GameConstant.MIN_NAME_LENGTH || name.length() > GameConstant.MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("1글자 이상 5글자 이하의 이름을 입력해주세요.");
        }
    }

    private void validateNoWhiteSpace(String name) {
        if (name.matches(GameConstant.WHITE_SPACE_EXISTS_REGEX)) {
            throw new IllegalArgumentException("이름에 공백이 없도록 입력해주세요.");
        }
    }

    public static List<Name> createNameList(String names) {
        String[] splitNames = splitNames(names);
        return Arrays.stream(splitNames)
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private static String[] splitNames(String names) {
        return names.split(",");
    }

    public static void validateDuplicateName(List<Name> nameList) {
        Set<Name> nameSet = new HashSet<>(nameList);
        if (nameSet.size() != nameList.size()) {
            throw new IllegalArgumentException("중복된 이름을 입력하셨습니다.");
        }
    }

    public String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Name otherName = (Name) o;
        return Objects.equals(name, otherName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}