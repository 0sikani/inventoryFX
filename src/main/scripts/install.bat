@echo off
echo ========================================
echo Inventory - Client Setup
echo ========================================
echo.

echo Checking Java Installation...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java Runtime Environment 25 or later
    echo Download from: https://adoptium.net/
    pause
    exit /b 1
)

echo Java found successfully!
echo.
echo Application Requirements:
echo - Backend API server must be running on http://localhost:8080
echo - MySQL database must be available
echo.
echo Installation completed!
echo.
echo To run the application:
echo 1. Ensure backend server is running
echo 2. Double-click student-management-client.exe
echo.
pause