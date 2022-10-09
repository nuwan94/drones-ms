package dev.nuwan.msdds.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StatusCodes {

  public static final Integer SUCCESS = 0;
  public static final Integer FAILURE = -1;
  public static final Integer DUPLICATE = 101;
  public static final Integer INVALID = 102;
  public static final Integer NOT_FOUND = 404;
}
