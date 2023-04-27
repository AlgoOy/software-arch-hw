cmake_minimum_required(VERSION 3.24)
project(software_arch_hw C)

set(CMAKE_C_STANDARD 11)

include_directories(
        MongoDB/Inc
        User/Inc
)

add_executable(
        software_arch_hw
        MongoDB/Inc/mongodb.h
        MongoDB/Src/mongodb.c
        User/Src/main.c
)
