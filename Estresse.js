import http from 'k6/http';
import { check, sleep } from 'k6';
import { Counter } from 'k6/metrics';

export let options = {
  stages: [
    { duration: '5s', target: 1 },
    { duration: '3s', target: 3 },
    { duration: '2s', target: 0 },
  ],
};

let status405Counter = new Counter('status_405_responses');
let qtd405 = 0;

function randomInt(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function randomPhone() {
  return Array.from({ length: 3 }, () => '9' + randomInt(10000000, 99999999).toString());
}

function randomString(length) {
  const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
  return Array.from({ length }, () => chars.charAt(Math.floor(Math.random() * chars.length))).join('');
}

export default function () {
  const payload = JSON.stringify({
    balance: 10.0,
    phoneNumbers: randomPhone(),
    name: "Novo Novo Facil de Ver",
    latitude: randomInt(100000, 999999),
    longitude: randomInt(100000, 999999),
    email: `leo${randomInt(1000, 9999)}@yahoo.com`,
    gender: "FEMALE",
    password: randomString(8) + "@123"
  });

  const headers = { 'Content-Type': 'application/json' };

  const res = http.put('http://localhost:8080/clients/1', payload, { headers });

  if (res.status === 405) {
    status405Counter.add(1);
    qtd405 += 1;
  }

  check(res, {
    'status 200 ou 201': (r) => r.status === 200 || r.status === 201,

  });

  sleep(1);
}