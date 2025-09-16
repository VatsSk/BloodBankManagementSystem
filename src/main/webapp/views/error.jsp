<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error - Something Went Wrong</title>
    <style>
        :root {
            --primary-color: #007bff;
            --background: #f4f6f8;
            --error-bg: #fff5f5;
            --error-color: #d32f2f;
            --border-color: #fbcfcf;
            --btn-hover: #0056b3;
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            background-color: var(--background);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .container {
            background-color: white;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 90%;
            text-align: center;
        }

        h1 {
            color: var(--error-color);
            margin-bottom: 10px;
            font-size: 28px;
        }

        p.message {
            background-color: var(--error-bg);
            color: var(--error-color);
            padding: 15px;
            border-radius: 8px;
            border: 1px solid var(--border-color);
            margin-bottom: 30px;
            font-size: 16px;
            word-break: break-word;
        }

        a.btn {
            display: inline-block;
            padding: 12px 24px;
            background-color: var(--primary-color);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        a.btn:hover {
            background-color: var(--btn-hover);
        }

        @media (max-width: 600px) {
            h1 {
                font-size: 22px;
            }

            .error-icon {
                font-size: 48px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Oops! Something went wrong</h1>
        <p class="message">
            <%= exception != null ? exception.getMessage() : "An unexpected error has occurred." %>
        </p>
        <a href="/dashboard" class="btn">Back to Dashboard</a>
    </div>
</body>
</html>
