package com.anuj.spoorthi.programs;

import java.util.List;

public interface ProgramService {
    String addProgram(ProgramRequest program);

    List<ProgramResponse> getAllPrograms();

    ProgramResponse updateProgram(ProgramRequest program, int id);
}
