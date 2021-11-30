package com.switchfully.digibooky.services.regex;

import java.util.regex.Pattern;

public class UserPattern {
    public static final Pattern emailPattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
}
