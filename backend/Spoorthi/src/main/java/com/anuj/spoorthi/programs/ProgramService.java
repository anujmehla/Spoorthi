package com.anuj.spoorthi.programs;

import java.util.List;
import java.util.UUID;

public interface ProgramService {
    String addProgram(ProgramRequest program);

    List<ProgramResponse> getAllPrograms();

    ProgramResponse updateProgram(ProgramRequest program, UUID id);
}
