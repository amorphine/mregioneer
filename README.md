# README #

MRegioneer is a library for direct working with Minecraft world files.
Thats not even an alpha version: the library is in early developing.

### How do I get set up? ###

Project uses Maven dependency system;
To run tests you should copy presents.ini in the same directory as local_presets.ini - that will be unversioned preset file;
WorldTest contains a summary of what was currently realized. It is a bit confusing but not more than reading tons of unofficial documentation. Help of improving tests is welcome;


### Why not WorldEdit? ###
WE is a plugin/mod which works with/via Minecraft running instance. Tha aim of MRegioneer is being able to work with MC world directly

### Why not MCEdit ###

Firstly, because of Python (I don't know Python :( )
Secodly, I doubt that MCEdit may be useful for any automatic tasks I need. For example, I have to copy some cuboid with given coordinates from mca-file to .schematic or directly into another mca-file. MRegioneer should be useful to create utils to do it via one command and I dont have to launch any GUI, which would be nice for servers or just CLI-expirienced users.

### Contribution guidelines ###

* Writing tests
* Code review
* Add short/useful comments to code

### Who do I talk to? ###

* My email is: fe-pavel@yandex.ru