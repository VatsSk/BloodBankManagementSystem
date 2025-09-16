<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Blood Bank System - Home</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    :root{
      --brand-red: #c82333;
      --accent: #ff6b6b;
      --muted: #6c757d;
    }

    html,body{
      height:100%;
      background: linear-gradient(180deg,#fff 0%, #fff7f7 40%);
      font-family: Inter, ui-sans-serif, system-ui, -apple-system, 'Segoe UI', Roboto, 'Helvetica Neue', Arial;
      -webkit-font-smoothing:antialiased;
      -moz-osx-font-smoothing:grayscale;
    }

    .page-wrap{
      min-height:100%;
      display:flex;
      align-items:center;
      justify-content:center;
      padding:48px 16px;
    }

    .card-home{
      width:100%;
      max-width:880px;
      background: linear-gradient(180deg, rgba(255,255,255,0.95), rgba(255,250,250,0.92));
      border-radius:16px;
      padding:34px;
      box-shadow: 0 10px 30px rgba(200,20,20,0.06), 0 2px 6px rgba(0,0,0,0.04);
      text-align:center;
    }

    h1{
      color:var(--brand-red);
      font-weight:700;
      margin-bottom:6px;
      letter-spacing:0.2px;
      font-size:2.1rem;
    }

    .lead{
      color:var(--muted);
      margin-bottom:22px;
      font-size:1.05rem;
    }

    .btn-row{
      display:flex;
      gap:14px;
      justify-content:center;
      flex-wrap:wrap;
      margin-top:6px;
    }

    .btn-custom{
      min-width:200px;
      border-radius:50px;
      padding:12px 20px;
      font-weight:600;
      transition: transform .18s ease, box-shadow .18s ease, opacity .18s ease;
      box-shadow: 0 6px 18px rgba(200,20,20,0.06);
    }

    .btn-custom:hover{
      transform: translateY(-4px);
      box-shadow: 0 16px 32px rgba(200,20,20,0.10);
    }

    .btn-primary{
      background: linear-gradient(90deg, var(--brand-red), #b71c1c);
      border: none;
      color: #fff;
    }

    .btn-success{
      background: linear-gradient(90deg, #28a745, #1e7e34);
      border: none;
      color: #fff;
    }

    .btn-warning{
      background: linear-gradient(90deg, #ffc107, #ffb020);
      border: none;
      color: #222;
    }

    /* subtle pulse on primary to draw attention */
    .btn-primary:focus{
      outline: none;
      box-shadow: 0 10px 30px rgba(200,20,20,0.12);
    }

    /* small decorative banner area (keeps page simple) */
    .banner{
      width:100%;
      height:190px;
      margin:14px auto 20px auto;
      border-radius:10px;
      background: linear-gradient(90deg, rgba(200,20,20,0.12), rgba(255,107,107,0.06));
      display:flex;
      align-items:center;
      justify-content:center;
      color:rgba(0,0,0,0.55);
      font-weight:600;
      letter-spacing:0.3px;
      backdrop-filter: blur(2px);
      box-shadow: 0 6px 20px rgba(200,20,20,0.04);
    }

    /* responsive tweaks */
    @media (max-width:576px){
      h1{font-size:1.6rem}
      .banner{height:140px}
      .btn-custom{min-width:160px;padding:10px 14px}
      .card-home{padding:22px}
    }
  </style>
</head>
<body>
  <div class="page-wrap">
    <div class="card-home">
      <h1 class="text-danger mt-2">Welcome to Blood Bank System</h1>
      <p class="lead">Your contribution can save lives. Get started now!</p>

      <div class="banner">Reliable : Secure : Community-driven</div>

      <div class="btn-row">
        <a href="/login" class="btn btn-primary btn-lg btn-custom">Login</a>
        <a href="/signup" class="btn btn-success btn-lg btn-custom">Signup</a>
        <a href="/forgotPassword" class="btn btn-warning btn-lg btn-custom">Forgot Password?</a>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
