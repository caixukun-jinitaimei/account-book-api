package com.example.accountbook1.model.server.http.response;

import java.io.Serializable;
import java.util.List;

public class RecognizePictureResponse implements Serializable {
    private int code;
    private String message;
    private String version;
    private double duration;
    private Result result;

    public static class Result implements Serializable{
        private int angle;
        private int width;
        private int height;
        private List<Line> lines;

        public static class Line implements Serializable{
            private String text;
            private double score;
            private String type;
            private List<Integer> position;
            private int angle;
            private int direction;
            private int handwritten;
            private List<Double> char_scores;
            private List<List<Integer>> char_centers;
            private List<List<Integer>> char_positions;
            private List<List<String>> char_candidates;
            private List<List<Double>> char_candidates_score;

            // getters and setters

            public Line() {
            }

            @Override
            public String toString() {
                return "Line{" +
                        "text='" + text + '\'' +
                        ", score=" + score +
                        ", type='" + type + '\'' +
                        ", position=" + position +
                        ", angle=" + angle +
                        ", direction=" + direction +
                        ", handwritten=" + handwritten +
                        ", char_scores=" + char_scores +
                        ", char_centers=" + char_centers +
                        ", char_positions=" + char_positions +
                        ", char_candidates=" + char_candidates +
                        ", char_candidates_score=" + char_candidates_score +
                        '}';
            }

            public Line(String text, double score, String type, List<Integer> position, int angle, int direction, int handwritten, List<Double> char_scores, List<List<Integer>> char_centers, List<List<Integer>> char_positions, List<List<String>> char_candidates, List<List<Double>> char_candidates_score) {
                this.text = text;
                this.score = score;
                this.type = type;
                this.position = position;
                this.angle = angle;
                this.direction = direction;
                this.handwritten = handwritten;
                this.char_scores = char_scores;
                this.char_centers = char_centers;
                this.char_positions = char_positions;
                this.char_candidates = char_candidates;
                this.char_candidates_score = char_candidates_score;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<Integer> getPosition() {
                return position;
            }

            public void setPosition(List<Integer> position) {
                this.position = position;
            }

            public int getAngle() {
                return angle;
            }

            public void setAngle(int angle) {
                this.angle = angle;
            }

            public int getDirection() {
                return direction;
            }

            public void setDirection(int direction) {
                this.direction = direction;
            }

            public int getHandwritten() {
                return handwritten;
            }

            public void setHandwritten(int handwritten) {
                this.handwritten = handwritten;
            }

            public List<Double> getChar_scores() {
                return char_scores;
            }

            public void setChar_scores(List<Double> char_scores) {
                this.char_scores = char_scores;
            }

            public List<List<Integer>> getChar_centers() {
                return char_centers;
            }

            public void setChar_centers(List<List<Integer>> char_centers) {
                this.char_centers = char_centers;
            }

            public List<List<Integer>> getChar_positions() {
                return char_positions;
            }

            public void setChar_positions(List<List<Integer>> char_positions) {
                this.char_positions = char_positions;
            }

            public List<List<String>> getChar_candidates() {
                return char_candidates;
            }

            public void setChar_candidates(List<List<String>> char_candidates) {
                this.char_candidates = char_candidates;
            }

            public List<List<Double>> getChar_candidates_score() {
                return char_candidates_score;
            }

            public void setChar_candidates_score(List<List<Double>> char_candidates_score) {
                this.char_candidates_score = char_candidates_score;
            }
        }

        // getters and setters

        public Result() {
        }

        @Override
        public String toString() {
            return "Result{" +
                    "angle=" + angle +
                    ", width=" + width +
                    ", height=" + height +
                    ", lines=" + lines +
                    '}';
        }

        public Result(int angle, int width, int height, List<Line> lines) {
            this.angle = angle;
            this.width = width;
            this.height = height;
            this.lines = lines;
        }

        public int getAngle() {
            return angle;
        }

        public void setAngle(int angle) {
            this.angle = angle;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public List<Line> getLines() {
            return lines;
        }

        public void setLines(List<Line> lines) {
            this.lines = lines;
        }
    }

    // getters and setters


    @Override
    public String toString() {
        return "RecognizePictureResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", version='" + version + '\'' +
                ", duration=" + duration +
                ", result=" + result +
                '}';
    }

    public RecognizePictureResponse() {
    }

    public RecognizePictureResponse(int code, String message, String version, double duration, Result result) {
        this.code = code;
        this.message = message;
        this.version = version;
        this.duration = duration;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
