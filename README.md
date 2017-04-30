# Readme

This sample program will split the given input files into smaller files according to the specified input buffer size. The split files will have their entries sorted and duplicates removed . After splitting, the smaller files will then be merged to one output file, comparing each line from each file and sorting them using a tree map, and then writing it to the output file.