
/*
 * Copyright © 2024 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
*/

package io.cdap.wrangler.parser;

import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.api.parser.TokenType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link TimeDuration} token
*/
public class TimeDurationTest {

    @Test
    public void testTimeDurationParsing() {
        // Basic values
        validateTimeDuration("1000ms", 1000); // assumed to be milliseconds
        validateTimeDuration("1s", 1000);
        validateTimeDuration("1S", 1000);
        validateTimeDuration("1m", 60 * 1000);
        validateTimeDuration("1M", 60 * 1000);
        validateTimeDuration("1h", 60 * 60 * 1000);
        validateTimeDuration("1H", 60 * 60 * 1000);
        validateTimeDuration("1d", 24 * 60 * 60 * 1000);
        validateTimeDuration("1D", 24 * 60 * 60 * 1000);

        // Decimal values
        validateTimeDuration("1.5s", (long) (1.5 * 1000));
        validateTimeDuration("2.5m", (long) (2.5 * 60 * 1000));
        validateTimeDuration("0.5h", (long) (0.5 * 60 * 60 * 1000));
        validateTimeDuration("0.25d", (long) (0.25 * 24 * 60 * 60 * 1000));

        // Larger values
        validateTimeDuration("90s", 90 * 1000);
        validateTimeDuration("120m", 120 * 60 * 1000);
    }

    private void validateTimeDuration(String value, long expectedMillis) {
        TimeDuration duration = new TimeDuration(value);
        Assert.assertEquals(value, duration.value());
        Assert.assertEquals(expectedMillis, duration.getMilliseconds());
        Assert.assertEquals(TokenType.TIME_DURATION, duration.type());
    }
}

