package com.project.unitech.util;

import com.project.unitech.exception.InvalidPinException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PinValidator {

    public static void validate(String pin) {

        PasswordValidator validator = new PasswordValidator(
                // length between 8 and 16 characters
                new LengthRule(7, 7),
                new AllowedRegexRule("^[A-Z0-9]+$"),
                new RepeatCharacterRegexRule(7),
                // define some illegal sequences that will fail when >= 5 chars long
                // alphabetical is of the form 'abcde', numerical is '34567', qwery is 'asdfg'
                // the false parameter indicates that wrapped sequences are allowed; e.g. 'xyzabc'
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 7, false),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 7, false)
        );

        RuleResult result = validator.validate(new PasswordData(pin));

        if(!result.isValid()) {
            log.error(String.format("Validation of %s pin failed", pin));
            throw new InvalidPinException();
        }

    }
}
