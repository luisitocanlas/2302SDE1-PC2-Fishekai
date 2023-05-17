package com.fishekai.engine;

class Introduction {
    private static final String FILE_PATH = "json/Game_Information.json";

    public void showIntro() {
        // access the json file
        // convert it so that we can easily access the key value pairs

        // display story
        String story = "You are a renowned archaeologist named Ethan Rutherford, known for your expertise in ancient artifacts. One fateful day, while exploring a mysterious ruin, you stumble upon a peculiar artifact—a small, intricately carved stone amulet. As you touch the amulet, a blinding light engulfs you, and you find yourself transported to an entirely different world.\nWhen you regain your senses, you discover that you are standing on a deserted island surrounded by the vast expanse of the ocean. Confused and disoriented, you realize that the amulet has somehow brought you to this unknown realm. As you explore your surroundings, you notice the island's lush foliage, pristine beaches, and a sense of both beauty and danger lurking in its depths.\nIn this new world, you quickly realize that survival is your utmost priority. You must scavenge for food, water, and shelter to sustain yourself. However, what sets this island apart is its mystical nature. Ancient magic permeates the land, providing you with both opportunities and challenges.\nAs you delve deeper into the island, you uncover the secrets of its magical properties. One of the key aspects you discover is the importance of fishing for your survival. The island's waters teem with an array of unique and magical marine life, presenting a rich opportunity for sustenance. However, you soon realize that traditional fishing methods won't suffice. To catch the magical creatures that inhabit these waters, you must learn to craft specialized tools and harness the power of the island's magic itself.\nAs you progress, you encounter other stranded individuals who have also been transported to this realm, each with their own unique skills and knowledge. Together, you must navigate the challenges of the island, unravel its mysteries, and find a way back home. The decisions you make, the relationships you build, and the skills you acquire will shape your fate and the outcome of your journey.\nPrepare to embark on a thrilling isekai adventure filled with magic, survival, and the pursuit of a way back home. Will you unravel the island's secrets and harness its magic to survive and thrive? The answers lie within the depths of the island and the bonds you form along the way.\n";
        System.out.printf("%s\n", story);

        // display objective
        String objective = "The objective of the game is to escape the island that you are transported into.";
        System.out.printf("%s\n\n", objective);

        // display character
        String character = "You are a renowned archaeologist named Ethan Rutherford, known for your expertise in ancient artifacts.";
        System.out.printf("%s\n\n", character);

        // display winning conditions
        String winningCondition = "You need to catch a certain legendary fish.";
        System.out.printf("%s\n\n", winningCondition);
    }

    // helper method to extract data from JSON file
    private void getData(String FILE_PATH) {

    }

    // for internal testing
    public static void main(String[] args) {
        Introduction intro = new Introduction();
        intro.showIntro();
    }
}