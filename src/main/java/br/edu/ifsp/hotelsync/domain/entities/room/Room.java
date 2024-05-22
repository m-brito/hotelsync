package br.edu.ifsp.hotelsync.domain.entities.room;

public class Room {
    private Long id;
    private int number;
    private int numberOfBeds;
    private String typeOfBed;
    private RoomCategory roomCategory;
    private String description;
    private RoomStatus roomStatus;
    private double area;

    public Room(Long id, int number, int numberOfBeds, String typeOfBed, RoomCategory roomCategory, String description, RoomStatus roomStatus, double area) {
        this.id = id;
        this.number = number;
        this.numberOfBeds = numberOfBeds;
        this.typeOfBed = typeOfBed;
        this.roomCategory = roomCategory;
        this.description = description;
        this.roomStatus = roomStatus;
        this.area = area;
    }

    public Room(int number, int numberOfBeds, String typeOfBed, RoomCategory roomCategory, String description, RoomStatus roomStatus, double area) {
        this.number = number;
        this.numberOfBeds = numberOfBeds;
        this.typeOfBed = typeOfBed;
        this.roomCategory = roomCategory;
        this.description = description;
        this.roomStatus = roomStatus;
        this.area = area;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getTypeOfBed() {
        return typeOfBed;
    }

    public void setTypeOfBed(String typeOfBed) {
        this.typeOfBed = typeOfBed;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
